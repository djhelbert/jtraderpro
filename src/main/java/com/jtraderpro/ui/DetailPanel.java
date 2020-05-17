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

import static com.jtraderpro.ui.AssetGroupPanel.COL_MAX;
import static com.jtraderpro.ui.AssetGroupPanel.ROW_MAX;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Detail Panel
 * 
 * @author djhelbert
 */
public class DetailPanel extends JPanel {

  private JPanel summaryPanel = new JPanel();
  private JPanel graphPanel = new JPanel();
  
  public DetailPanel() {
    super();
    init();
  }

  private void init() {
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    setLayout(new GridLayout(2, 1));
    add(summaryPanel);
    add(graphPanel);
  }

  public void update(String symbol) {
  }
}
