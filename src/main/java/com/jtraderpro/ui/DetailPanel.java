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
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Detail Panel
 *
 * @author djhelbert
 */
public class DetailPanel extends JPanel {

  private final JPanel summaryPanel = new JPanel();
  private final JLabel priceLabel = new JLabel();
  private final JLabel changeLabel = new JLabel();
  private final JLabel symbolLabel = new JLabel();
  private final JLabel openLabel = new JLabel();
  private final JLabel bidLabel = new JLabel();
  private final JLabel askLabel = new JLabel();
  private final JLabel volumeLabel = new JLabel();
  private final JLabel avgVolumeLabel = new JLabel();
  private final JLabel yearHighLabel = new JLabel();
  private final JLabel yearLowLabel = new JLabel();
  private final JLabel dividendLabel = new JLabel();
  private final JLabel exDivLabel = new JLabel();
  private final JLabel dayHighLabel = new JLabel();
  private final JLabel dayLowLabel = new JLabel();
  private final JLabel epsLabel = new JLabel();
  private final JLabel peLabel = new JLabel();
  private final JLabel pBookLabel = new JLabel();
  private final JLabel exchangeLabel = new JLabel();
  
  private JPanel graphPanel = new JPanel();

  private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy");
  private static final Color DARK_GREEN = new Color(51, 102, 0);

  public DetailPanel() {
    super();
    init();
  }

  private void init() {
    summaryPanel.setLayout(new GridLayout(9, 4));

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

    setBorder(BorderFactory.createTitledBorder(""));
    setLayout(new GridLayout(2, 1));

    add(summaryPanel);
    add(graphPanel);

  }

  private void addLabel(String text, JLabel label) {
    summaryPanel.add(new JLabel(text));
    summaryPanel.add(label);
  }

  private String formatDouble(Double value) {
    if(value == null) {
      return "";
    }
    
    return decimalFormat.format(value);
  }

  public void update(String symbol) {
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

      dividendLabel.setText(decimalFormat.format(info.getDividendYield()) + (info.getDividendYield() == null ? "" : "%") );

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
    }
  }
}
