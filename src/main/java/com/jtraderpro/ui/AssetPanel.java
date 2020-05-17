/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.ui;

import com.jtraderpro.model.Asset;
import com.jtraderpro.service.AssetInfo;
import com.jtraderpro.service.AssetService;
import java.awt.Color;
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
    updateInfo();
  }

  public final void updateInfo() {
    final AssetInfo info = AssetService.getInstance().getAssetInfo(asset.getSymbol());
    
    if(info != null) {
      priceLabel.setText(info.getMarketPrice().toString() + " " + info.getPercentChange());
      volumeLabel.setText((info.getVolume().doubleValue() / 1000000) + "M");

      if(info.getPercentChange() < 0.0) {
        priceLabel.setForeground(Color.red);
        symbolLabel.setForeground(Color.red);
      } else if( info.getPercentChange() > 0.0) {
        priceLabel.setForeground(new Color(51,102,0));
        symbolLabel.setForeground(new Color(51,102,0));
      } else {
        priceLabel.setForeground(Color.black);
        symbolLabel.setForeground(Color.black);
      }
    }
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
