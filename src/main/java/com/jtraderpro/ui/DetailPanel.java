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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  private static final JLabel epsLabel = new JLabel();
  private static final JLabel peLabel = new JLabel();
  private static final JLabel pBookLabel = new JLabel();
  private static final JLabel exchangeLabel = new JLabel();
  private static final JPanel graphPanel = new JPanel();
  private static final CardLayout cardLayout = new CardLayout();
  private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy");
  private static final Color DARK_GREEN = new Color(51, 102, 0);
  private static final Dimension CHART_SIZE = new Dimension(400, 400);
  private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
  private static final Logger logger = LoggerFactory.getLogger(DetailPanel.class);
  private static Component lastComponent;

  public DetailPanel() {
    super();
    init();
    executor.scheduleAtFixedRate(new UpdateDetailTask(), 60, 60, TimeUnit.SECONDS);
  }

  private void init() {
    setBackground(Color.white);

    summaryPanel.setLayout(new GridLayout(9, 4));
    summaryPanel.setPreferredSize(CHART_SIZE);
    summaryPanel.setBackground(Color.white);

    addLabel("Symbol ", symbolLabel);
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
    addLabel("EPS ", epsLabel);
    addLabel("PE Ratio ", peLabel);
    addLabel("PB Ratio ", pBookLabel);
    addLabel("Exchange ", exchangeLabel);

    graphPanel.setPreferredSize(CHART_SIZE);
    graphPanel.setLayout(cardLayout);
    graphPanel.setBackground(Color.white);

    setBorder(BorderFactory.createTitledBorder(" "));
    setLayout(new GridLayout(2, 1));

    add(summaryPanel);
    add(graphPanel);
  }

  private void addLabel(String text, JLabel label) {
    summaryPanel.add(new JLabel(text));
    summaryPanel.add(label);
  }

  private String formatDouble(Double value) {
    if (value == null) {
      return "";
    }

    return decimalFormat.format(value);
  }

  private void refresh() {
    if (symbolLabel.getText() != null && symbolLabel.getText().length() > 0) {
      update(symbolLabel.getText());
    }
  }

  public synchronized void update(String symbol) {
    final AssetInfo info = AssetService.getInstance().getAssetInfo(symbol, true);

    if (info != null) {
      setBorder(BorderFactory.createTitledBorder(info.getName()));

      symbolLabel.setText(info.getSymbol());
      openLabel.setText(formatDouble(info.getOpen()));
      askLabel.setText(formatDouble(info.getBid()) + "x" + info.getBidSize());
      bidLabel.setText(formatDouble(info.getAsk()) + "x" + info.getAskSize());
      yearHighLabel.setText(formatDouble(info.getYearHigh()));
      yearLowLabel.setText(formatDouble(info.getYearLow()));
      dayHighLabel.setText(formatDouble(info.getDayHigh()));
      dayLowLabel.setText(formatDouble(info.getDayLow()));
      priceLabel.setText(formatDouble(info.getMarketPrice()));
      changeLabel.setText(formatDouble(info.getPercentChange()) + "%");
      epsLabel.setText(formatDouble(info.getEps()));
      peLabel.setText(formatDouble(info.getPe()));
      pBookLabel.setText(formatDouble(info.getPriceBook()));
      exchangeLabel.setText(info.getExchange());

      if (info.getVolume() > 1000000) {
        volumeLabel.setText((info.getVolume() / 1000000) + "M");
      } else {
        volumeLabel.setText((info.getVolume() / 1000) + "K");
      }

      if (info.getAvgVolume() > 1000000) {
        avgVolumeLabel.setText((info.getAvgVolume() / 1000000) + "M");
      } else {
        avgVolumeLabel.setText((info.getAvgVolume() / 1000) + "K");
      }

      dividendLabel.setText(decimalFormat.format(info.getDividendYield()) + (info.getDividendYield() == null ? "" : "%"));

      if (info.getExDate() != null) {
        exDivLabel.setText(dateFormat.format(info.getExDate()));
      } else {
        exDivLabel.setText("");
      }

      if (info.getPercentChange() < 0.0) {
        priceLabel.setForeground(Color.red);
        changeLabel.setForeground(Color.red);
      } else if (info.getPercentChange() > 0.0) {
        priceLabel.setForeground(DARK_GREEN);
        changeLabel.setForeground(DARK_GREEN);
      } else {
        priceLabel.setForeground(Color.black);
        changeLabel.setForeground(Color.black);
      }

      // Remove previous chart
      if (lastComponent != null) {
        graphPanel.remove(lastComponent);
      }

      // Create new chart
      final Component newComp = ChartUtil.createChart(info.getSymbol(), info.getCurrency(), info.getAssetQuotes());
      newComp.setPreferredSize(CHART_SIZE);

      // Add to card layout
      graphPanel.add(newComp, info.getSymbol());
      graphPanel.validate();

      lastComponent = newComp;
    }
  }

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
