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
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Detail Panel
 *
 * @author djhelbert
 */
public class DetailPanel extends JPanel {

  private TitledBorder titledBorder = BorderFactory.createTitledBorder("");

  private JPanel summaryPanel = new JPanel();
  private JLabel priceLabel = new JLabel();
  private JLabel changeLabel = new JLabel();
  private JLabel symbolLabel = new JLabel();
  private JLabel openLabel = new JLabel();
  private JLabel bidLabel = new JLabel();
  private JLabel askLabel = new JLabel();
  private JLabel volumeLabel = new JLabel();
  private JLabel avgVolumeLabel = new JLabel();
  private JLabel yearHighLabel = new JLabel();
  private JLabel yearLowLabel = new JLabel();
  private JLabel dividendLabel = new JLabel();
  private JLabel exDivLabel = new JLabel();
  private JLabel dayHighLabel = new JLabel();
  private JLabel dayLowLabel = new JLabel();
  
  private JPanel graphPanel = new JPanel();

  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yy");
  
  public DetailPanel() {
    super();
    init();
  }

  private void init() {
    summaryPanel.setLayout(new GridLayout(7, 4));
    
    summaryPanel.add(new JLabel("Symbol "));
    summaryPanel.add(symbolLabel);
    summaryPanel.add(new JLabel("Open "));
    summaryPanel.add(openLabel);
    summaryPanel.add(new JLabel("Price "));
    summaryPanel.add(priceLabel);
    summaryPanel.add(new JLabel("Change "));
    summaryPanel.add(changeLabel);
    summaryPanel.add(new JLabel("Bid "));
    summaryPanel.add(bidLabel);
    summaryPanel.add(new JLabel("Ask "));
    summaryPanel.add(askLabel);
    summaryPanel.add(new JLabel("Volume "));
    summaryPanel.add(volumeLabel);
    summaryPanel.add(new JLabel("Avg. Volume "));
    summaryPanel.add(avgVolumeLabel);
    summaryPanel.add(new JLabel("Day Low "));
    summaryPanel.add(dayLowLabel);
    summaryPanel.add(new JLabel("Day Low "));
    summaryPanel.add(dayHighLabel);
    summaryPanel.add(new JLabel("Year Low "));
    summaryPanel.add(yearLowLabel);
    summaryPanel.add(new JLabel("Year Low "));
    summaryPanel.add(yearHighLabel);
    summaryPanel.add(new JLabel("Dividend "));
    summaryPanel.add(dividendLabel);
    summaryPanel.add(new JLabel("Ex Date "));
    summaryPanel.add(exDivLabel);

    setBorder(titledBorder);
    setLayout(new GridLayout(2, 1));
    add(summaryPanel);
    add(graphPanel);

  }

  private String formatDouble(Double value) {
    return decimalFormat.format(value);
  }

  public void update(String symbol) {
    final AssetInfo info = AssetService.getInstance().getAssetInfo(symbol);

    if (info != null) {
      titledBorder.setTitle(info.getName());
      symbolLabel.setText(info.getSymbol());
      openLabel.setText(formatDouble(info.getOpen()));
      askLabel.setText(formatDouble(info.getBid()));
      bidLabel.setText(formatDouble(info.getAsk()));
      yearHighLabel.setText(formatDouble(info.getYearHigh()));
      yearLowLabel.setText(formatDouble(info.getYearLow()));
      dayHighLabel.setText(formatDouble(info.getDayHigh()));
      dayLowLabel.setText(formatDouble(info.getDayLow()));
      priceLabel.setText(formatDouble(info.getMarketPrice()));
      changeLabel.setText(formatDouble(info.getPercentChange()));

      if (info.getVolume() > 1000000) {
        volumeLabel.setText((info.getVolume()/1000000) + "M");
      } else {
        volumeLabel.setText((info.getVolume()/1000) + "K");
      }

      if (info.getAvgVolume() > 1000000) {
        avgVolumeLabel.setText((info.getAvgVolume() / 1000000) + "M");
      } else {
        avgVolumeLabel.setText((info.getAvgVolume() / 1000) + "K");
      }
      
      dividendLabel.setText(decimalFormat.format(info.getDividendYield()));

      if(info.getExDate() != null) {
        exDivLabel.setText(dateFormat.format(info.getExDate()));
      }
    }
  }
}
