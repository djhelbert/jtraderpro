/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.ui;

import com.jtraderpro.model.Asset;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Asset Panel
 *
 * @author djhelbert
 */
public class AssetPanel extends JPanel {

  private JLabel symbolLabel = new JLabel("", JLabel.CENTER);
  private JLabel priceLabel = new JLabel("", JLabel.CENTER);
  private JLabel volumeLabel = new JLabel("", JLabel.CENTER);
  private Asset asset;

  public AssetPanel() {
    super();
    init();
  }

  public AssetPanel(Asset asset) {
    super();
    init();
    update(asset);
  }

  public final void update(Asset asset) {
    this.asset = asset;
    symbolLabel.setText(asset.getSymbol());
    symbolLabel.setToolTipText(asset.getName());
  }

  public final void clear() {
    asset = null;
    symbolLabel.setText("");
    priceLabel.setText("");
    volumeLabel.setText("");
  }

  private void init() {
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    setLayout(new GridLayout(3, 1));

    add(symbolLabel);
    add(priceLabel);
    add(volumeLabel);
  }
}
