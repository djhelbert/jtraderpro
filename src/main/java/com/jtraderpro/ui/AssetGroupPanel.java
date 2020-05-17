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

  public static int ROW_MAX = 8;
  public static int COL_MAX = 5;
  public static int MAX_SIZE = 40;

  private final List<AssetPanel> assetPanels = new ArrayList<>();
  
  private AssetGroup group;

  public AssetGroupPanel() {
    this(new AssetGroup());
  }

  public AssetGroupPanel(AssetGroup group) {
    super();
    this.group = group;
    init();
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
    for (Asset asset : group.getAssets()) {
      assetPanels.get(asset.getOrder()).update(asset);
    }
  }

  private void init() {
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    setLayout(new GridLayout(ROW_MAX, COL_MAX));

    int order = 0;

    for (int i = 0; i < ROW_MAX; i++) {
      for (int j = 0; j < COL_MAX; j++) {
        AssetPanel panel = new AssetPanel(group, order);
        add(panel);
        assetPanels.add(panel);
        order++;
      }
    }
  }
}
