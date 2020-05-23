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

import com.jtraderpro.model.Asset;
import com.jtraderpro.model.AssetGroup;
import com.jtraderpro.service.AssetInfo;
import com.jtraderpro.service.AssetService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 * Asset Panel
 *
 * @author djhelbert
 */
public class AssetPanel extends JPanel implements MouseListener, ActionListener {

  private final JPopupMenu assetMenu = new JPopupMenu("Asset");
  private final JMenuItem updateItem = new JMenuItem("Update");
  private final JMenuItem clearItem = new JMenuItem("Clear");
  private final JLabel symbolLabel = new JLabel("", JLabel.CENTER);
  private final JLabel priceLabel = new JLabel("", JLabel.CENTER);
  private final JLabel volumeLabel = new JLabel("", JLabel.CENTER);
  private final JLabel valueLabel = new JLabel("", JLabel.CENTER);
  private final int order;
  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
  private final AssetGroup assetGroup;
  private Asset asset;
  private Font labelFont;
  private Font boldFont;

  private static final Color DARK_GREEN = new Color(51, 102, 0);
  
  /**
   * Constructor
   *
   * @param assetGroup
   * @param order
   */
  public AssetPanel(AssetGroup assetGroup, Integer order) {
    super();
    init();
    this.order = order;
    this.assetGroup = assetGroup;
  }

  /**
   * Refresh Panel
   */
  public final void refresh() {
    symbolLabel.setText(asset.getSymbol());
    symbolLabel.setToolTipText(asset.getName());
    updateInfo();
  }

  /**
   * Refresh for Asset
   *
   * @param asset
   */
  public final void refresh(Asset asset) {
    this.asset = asset;
    symbolLabel.setText(asset.getSymbol());
    symbolLabel.setToolTipText(asset.getName());
    updateInfo();
  }

  public final void updateInfo() {
    if (asset != null) {
      final AssetInfo info = AssetService.getInstance().getAssetInfo(asset.getSymbol());

      if (info != null) {
        priceLabel.setText(
            formatDouble(info.getMarketPrice()) + " " + formatDouble(info.getPercentChange())
                + "%");

        if (info.getVolume() > 1000000) {
          volumeLabel.setText((info.getVolume() / 1000000) + "M");
        } else {
          volumeLabel.setText((info.getVolume() / 1000) + "K");
        }

        if (info.getPercentChange() < 0.0) {
          priceLabel.setForeground(Color.red);
          symbolLabel.setForeground(Color.red);
        } else if (info.getPercentChange() > 0.0) {
          priceLabel.setForeground(DARK_GREEN);
          symbolLabel.setForeground(DARK_GREEN);
        } else {
          priceLabel.setForeground(Color.black);
          symbolLabel.setForeground(Color.black);
        }
      }
    }
  }

  private String formatDouble(Double value) {
    return decimalFormat.format(value);
  }

  /**
   * Clear Panel
   */
  public final void empty() {
    symbolLabel.setText("");
    priceLabel.setText("");
    volumeLabel.setText("");
    valueLabel.setText("");
    asset = null;
  }

  /**
   * Clear Panel
   */
  public final void clear() {
    assetGroup.removeAsset(asset);
    symbolLabel.setText("");
    priceLabel.setText("");
    volumeLabel.setText("");
    valueLabel.setText("");
    asset = null;
  }

  /**
   * Initialize Component
   */
  private void init() {
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    setLayout(new GridLayout(4, 1));

    add(symbolLabel);
    add(priceLabel);
    add(volumeLabel);
    add(valueLabel);

    addMouseListener(this);
    symbolLabel.addMouseListener(this);
    priceLabel.addMouseListener(this);
    volumeLabel.addMouseListener(this);
    valueLabel.addMouseListener(this);

    assetMenu.add(updateItem);
    assetMenu.add(clearItem);

    clearItem.addActionListener(this);
    updateItem.addActionListener(this);

    labelFont = symbolLabel.getFont();
    boldFont = new Font(labelFont.getName(), Font.BOLD, labelFont.getSize()+1);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      assetMenu.show(e.getComponent(), e.getX(), e.getY());
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    checkPopup(e);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if(e.getSource().equals(symbolLabel)) {
      symbolLabel.setFont(boldFont); // Update font
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if(e.getSource().equals(symbolLabel)) {
      symbolLabel.setFont(labelFont); // Revert
    }
  }

  private void checkPopup(MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
      assetMenu.show(e.getComponent(), e.getX(), e.getY());
    } else if (SwingUtilities.isLeftMouseButton(e)) {
      if (asset != null) {
        PortfolioPanel.getDetailPanel().update(asset.getSymbol());
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(clearItem)) {
      clear();
    } else if (e.getSource().equals(updateItem)) {
      final String input = JOptionPane.showInputDialog(
          MainFrame.getMainComponent(), "Enter new symbol", "Add", JOptionPane.QUESTION_MESSAGE);
      if (input != null && !input.isBlank()) {
        final AssetInfo info = AssetService.getInstance().getAssetInfo(input.trim());

        if (info != null) {
          final Asset newAsset = new Asset(input.toUpperCase().trim(), info.getName(), order);

          if (asset != null) {
            // Update asset
            asset.setSymbol(newAsset.getSymbol());
            asset.setName(info.getName());
            refresh();
          } else if (!assetGroup.getAssets().contains(newAsset)) {
            // Add new asset
            assetGroup.addAsset(newAsset);
            refresh(newAsset);
          } else {
            JOptionPane.showMessageDialog(MainFrame.getMainComponent(), "Symbol has already been added.",
                "Error", JOptionPane.ERROR_MESSAGE);
          }
        } else {
          JOptionPane.showMessageDialog(MainFrame.getMainComponent(), "Not a valid symbol.",
              "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
