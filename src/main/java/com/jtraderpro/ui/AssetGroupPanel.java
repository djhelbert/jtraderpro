/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.ui;

import com.jtraderpro.model.Asset;
import com.jtraderpro.model.AssetGroup;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Asset Group Panel
 *
 * @author djhelbert
 */
public class AssetGroupPanel extends JPanel {

  public static int ROW_MAX = 6;
  public static int COL_MAX = 6;
  public static int MAX_SIZE = 36;

  private final List<AssetPanel> assetPanels = new ArrayList<>();

  private AssetGroup group;

  public AssetGroupPanel() {
    this(new AssetGroup());
  }

  public AssetGroupPanel(AssetGroup group) {
    super();
    init();
    this.group = group;
    update();
  }

  public int getOrder() {
    return group.getOrder();
  }
  
  private void clear() {
    this.group = new AssetGroup();
    update();
  }

  private void update() {
    for(Asset asset : group.getAssets()) {
      assetPanels.get(asset.getOrder()).update(asset);
    }
  }

  private void init() {
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    setLayout(new GridLayout(ROW_MAX, COL_MAX));

    for(int i=0; i< ROW_MAX; i++) {
      for(int j=0; j< COL_MAX; j++) {
        AssetPanel panel = new AssetPanel();
        add(panel);
        assetPanels.add(panel);
      }
    }
  }
}
