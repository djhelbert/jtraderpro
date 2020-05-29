/*
 * jTraderPro is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jTraderPro is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jTraderPro.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.jtraderpro.ui;

import com.jtraderpro.service.AssetInfo;
import com.jtraderpro.service.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Detail Panel
 *
 * @author djhelbert
 */
public class DetailPanel extends JPanel {

    private static final JPanel summaryPanel = new JPanel();
    private static final JLabel priceLabel = new JLabel();
    private static final JLabel changeLabel = new JLabel();
    private static final JLabel symbolLabel = new JLabel();
    private static final JLabel openLabel = new JLabel();
    private static final JLabel bidLabel = new JLabel();
    private static final JLabel askLabel = new JLabel();
    private static final JLabel volumeLabel = new JLabel();
    private static final JLabel avgVolumeLabel = new JLabel();
    private static final JLabel yearHighLabel = new JLabel();
    private static final JLabel yearLowLabel = new JLabel();
    private static final JLabel dividendLabel = new JLabel();
    private static final JLabel exDivLabel = new JLabel();
    private static final JLabel dayHighLabel = new JLabel();
    private static final JLabel dayLowLabel = new JLabel();
    private static final JLabel rsiLabel = new JLabel();
    private static final JLabel fiftyDayLabel = new JLabel();
    private static final JLabel epsLabel = new JLabel();
    private static final JLabel peLabel = new JLabel();
    private static final JLabel pBookLabel = new JLabel();
    private static final JLabel rocLabel = new JLabel();
    private static final JLabel mktCapLabel = new JLabel();
    private static final JLabel earningsDateLabel = new JLabel();
    private static final JPanel graphPanel = new JPanel();
    private static final CardLayout cardLayout = new CardLayout();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy");
    private static final Color DARK_GREEN = new Color(51, 102, 0);
    private static final Dimension CHART_SIZE = new Dimension(400, 400);
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static final Logger logger = LoggerFactory.getLogger(DetailPanel.class);
    private static Component lastComponent;

    /**
     * Constructor
     *
     * @param symbol Default Symbol
     */
    public DetailPanel(String symbol) {
        super();
        init();
        if (symbol != null) {
            update(symbol);
        }
        executor.scheduleAtFixedRate(new UpdateDetailTask(), 60, 60, TimeUnit.SECONDS);
    }

    private void init() {
        setBackground(Color.white);

        summaryPanel.setLayout(new GridLayout(11, 4));
        summaryPanel.setPreferredSize(CHART_SIZE);
        summaryPanel.setBackground(Color.white);

        addLabel("Symbol ", symbolLabel);
        addLabel("Market Cap ", mktCapLabel);
        addLabel("Earnings Date ", earningsDateLabel);
        addLabel("Open ", openLabel);
        addLabel("Price ", priceLabel);
        addLabel("Change ", changeLabel);
        addLabel("Bid ", bidLabel);
        addLabel("Ask ", askLabel);
        addLabel("Volume ", volumeLabel);
        addLabel("Avg. Volume ", avgVolumeLabel);
        addLabel("Day Low ", dayLowLabel);
        addLabel("Day Low ", dayHighLabel);
        addLabel("Year Low ", yearLowLabel);
        addLabel("Year High ", yearHighLabel);
        addLabel("Dividend ", dividendLabel);
        addLabel("Payment Date ", exDivLabel);
        addLabel("EPS ", epsLabel, "Earnings Per Share");
        addLabel("PE Ratio ", peLabel, "Price/Earnings Ratio");
        addLabel("PB Ratio ", pBookLabel, "Price/Book Ratio");
        addLabel("ROC ", rocLabel, "Rate of Change");
        addLabel("RSI ", rsiLabel, "Relative Strength Index (14 days)");
        addLabel("50 Day Avg. ", fiftyDayLabel, "Average Price (50 days)");

        graphPanel.setPreferredSize(CHART_SIZE);
        graphPanel.setLayout(cardLayout);
        graphPanel.setBackground(Color.white);

        setBorder(BorderFactory.createTitledBorder(" "));
        setLayout(new GridLayout(2, 1));

        add(summaryPanel);
        add(graphPanel);
    }

    /**
     * Add Labels to Summary Panel w/Tool Tip
     *
     * @param text    Text
     * @param label   Label
     * @param tooltip Tool Tip Text
     */
    private void addLabel(String text, JLabel label, String tooltip) {
        label.setToolTipText(tooltip);
        summaryPanel.add(new JLabel(text));
        summaryPanel.add(label);
    }

    /**
     * Add Labels to Summary Panel
     *
     * @param text  Text
     * @param label Label
     */
    private void addLabel(String text, JLabel label) {
        summaryPanel.add(new JLabel(text));
        summaryPanel.add(label);
    }

    /**
     * Format Double to String
     *
     * @param value Value
     * @return Formatted String
     */
    private String formatDouble(Double value) {
        if (value == null) {
            return "";
        }

        return decimalFormat.format(value);
    }

    private String formatLong(Long value) {
        if(value == null) {
            return "";
        }

        if (value > 1000000000) {
            return (value / 1000000000) + "B";
        } else if (value > 1000000) {
            return (value / 1000000) + "M";
        } else if (value > 0) {
            return (value / 1000) + "K";
        } else {
            return value.toString();
        }
    }
    /**
     * Refresh w/Same Symbol
     */
    private void refresh() {
        if (symbolLabel.getText() != null && symbolLabel.getText().length() > 0) {
            update(symbolLabel.getText());
        }
    }

    /**
     * Update for new Symbol
     *
     * @param symbol Symbol
     */
    public synchronized void update(String symbol) {
        final AssetInfo info = AssetService.getInstance().getAssetInfo(symbol, true);

        if (info != null) {
            setBorder(BorderFactory.createTitledBorder(info.getName()));

            symbolLabel.setText(info.getSymbol());
            openLabel.setText(formatDouble(info.getOpen()));
            askLabel.setText(formatDouble(info.getBid()) + " x " + info.getBidSize());
            bidLabel.setText(formatDouble(info.getAsk()) + " x " + info.getAskSize());
            yearHighLabel.setText(formatDouble(info.getYearHigh()));
            yearLowLabel.setText(formatDouble(info.getYearLow()));
            dayHighLabel.setText(formatDouble(info.getDayHigh()));
            dayLowLabel.setText(formatDouble(info.getDayLow()));
            priceLabel.setText(formatDouble(info.getMarketPrice()));
            epsLabel.setText(formatDouble(info.getEps()));
            peLabel.setText(formatDouble(info.getPe()));
            pBookLabel.setText(formatDouble(info.getPriceBook()));
            rocLabel.setText(formatDouble(info.getRoc()));
            rsiLabel.setText(formatDouble(info.getRsi()));
            fiftyDayLabel.setText(formatDouble(info.getFiftyDayAvg()));

            mktCapLabel.setText(formatLong(info.getMarketCap()));
            volumeLabel.setText(formatLong(info.getVolume()));
            avgVolumeLabel.setText(formatLong(info.getAvgVolume()));

            if(info.getAnnualYield() != null && info.getAnnualYield() > 0.0) {
                dividendLabel.setText(decimalFormat.format(info.getAnnualYield()) + " " + decimalFormat.format(info.getDividendYield()) + (info.getDividendYield() == null ? "" : "%"));
            } else {
                dividendLabel.setText("");
            }

            if (info.getExDate() != null) {
                exDivLabel.setText(dateFormat.format(info.getExDate()));
            } else {
                exDivLabel.setText("");
            }

            if (info.getEarningsAnnouncement() != null) {
                earningsDateLabel.setText(dateFormat.format(info.getEarningsAnnouncement()));
            } else {
                earningsDateLabel.setText("");
            }

            if (info.getPercentChange() < 0.0) {
                changeLabel.setText(formatDouble(info.getPercentChange()) + "%");
                priceLabel.setForeground(Color.red);
                changeLabel.setForeground(Color.red);
            } else if (info.getPercentChange() > 0.0) {
                changeLabel.setText("+" + formatDouble(info.getPercentChange()) + "%");
                priceLabel.setForeground(DARK_GREEN);
                changeLabel.setForeground(DARK_GREEN);
            } else {
                changeLabel.setText(formatDouble(info.getPercentChange()) + "%");
                priceLabel.setForeground(Color.black);
                changeLabel.setForeground(Color.black);
            }

            // Remove previous chart
            if (lastComponent != null) {
                graphPanel.remove(lastComponent);
            }

            // Create new chart
            final Component newComp = ChartUtil.createChart(info.getSymbol() + " " + info.getExchange(), info.getCurrency(), info.getAssetQuotes());
            newComp.setPreferredSize(CHART_SIZE);

            // Add to card layout
            graphPanel.add(newComp, info.getSymbol());
            graphPanel.validate();

            lastComponent = newComp;
        }
    }

    /**
     * Runnable Task for Refresh
     */
    private class UpdateDetailTask implements Runnable {
        public UpdateDetailTask() {
        }

        @Override
        public void run() {
            try {
                refresh();
            } catch (Exception e) {
                logger.error("Update Detail Task", e);
            }
        }
    }
}
