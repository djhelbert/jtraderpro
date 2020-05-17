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

import com.jtraderpro.PortfolioProvider;
import com.jtraderpro.model.AssetGroup;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

/**
 * Portfolio Panel
 * 
 * @author djhelbert
 */
public class PortfolioPanel extends JPanel {
  
  private final JPanel buttonPanel = new JPanel();
  private final JButton addGroupButton = new JButton("Group");
  private final JButton saveButton = new JButton("Save");
  private final JTabbedPane tabbedPane = new JTabbedPane();
  private final DetailPanel detailPanel = new DetailPanel();
  
  public PortfolioPanel() {
    super();
    init();
  }

  private void init() {
    load();
    
    buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    saveButton.setIcon(Util.getImageIcon("save.png"));
    addGroupButton.setIcon(Util.getImageIcon("add.png"));

    buttonPanel.add(saveButton);
    buttonPanel.add(addGroupButton);

    setLayout(new BorderLayout());
    
    add(buttonPanel, BorderLayout.PAGE_START);
    add(tabbedPane, BorderLayout.CENTER);
    add(detailPanel, BorderLayout.LINE_END);
  }

  public void load() {
    tabbedPane.removeAll();

    for(AssetGroup group : PortfolioProvider.getInstance().getPortfolio().getGroups()) {
      tabbedPane.addTab(group.getName(), new AssetGroupPanel(group));
    }
  }
}
