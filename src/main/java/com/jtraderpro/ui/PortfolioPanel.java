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

import com.jtraderpro.Main;
import com.jtraderpro.PortfolioProvider;
import com.jtraderpro.model.AssetGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Portfolio Panel
 * 
 * @author djhelbert
 */
public class PortfolioPanel extends JPanel implements ActionListener {

  private static final JPanel buttonPanel = new JPanel();
  private static final JButton addGroupButton = new JButton("Group");
  private static final JButton remGroupButton = new JButton("Group");
  private static final JButton saveButton = new JButton("Save");
  private static final JTabbedPane tabbedPane = new JTabbedPane();
  private static final DetailPanel detailPanel = new DetailPanel();

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public PortfolioPanel() {
    super();
    init();
  }

  private void init() {
    load();
    
    buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    saveButton.addActionListener(this);
    saveButton.setIcon(Util.getImageIcon("save.png"));
    addGroupButton.addActionListener(this);
    addGroupButton.setIcon(Util.getImageIcon("add.png"));
    remGroupButton.addActionListener(this);
    remGroupButton.setIcon(Util.getImageIcon("delete.png"));
    buttonPanel.add(saveButton);
    buttonPanel.add(addGroupButton);
    buttonPanel.add(remGroupButton);

    setBackground(Color.white);
    setLayout(new BorderLayout());
    
    add(buttonPanel, BorderLayout.PAGE_START);
    add(tabbedPane, BorderLayout.CENTER);
    add(detailPanel, BorderLayout.LINE_END);
  }

  public static DetailPanel getDetailPanel() {
    return detailPanel;
  }

  public void load() {
    tabbedPane.removeAll();

    for(AssetGroup group : PortfolioProvider.getInstance().getPortfolio().getGroups()) {
      tabbedPane.addTab(group.getName(), new AssetGroupPanel(group));
    }

    if(tabbedPane.getTabCount() > 1) {
      remGroupButton.setEnabled(true);
    } else {
      remGroupButton.setEnabled(false);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource().equals(saveButton)) {
      try {
        PortfolioProvider.getInstance().save();
      } catch(Exception err) {
        logger.error("Save Error", err);
      }
    } else if(e.getSource().equals(remGroupButton)) {
      if(tabbedPane.getTabCount() > 1) {
        int index = tabbedPane.getSelectedIndex();
        final AssetGroupPanel agPanel = (AssetGroupPanel)tabbedPane.getSelectedComponent();
        PortfolioProvider.getInstance().getPortfolio().getGroups().remove(agPanel.getAssetGroup());
        tabbedPane.remove(index);

        if(tabbedPane.getTabCount() <= 1) {
          remGroupButton.setEnabled(false);
        }
      }
    } else {
      final String input = JOptionPane.showInputDialog(
          MainFrame.getMainComponent(), "Enter new name", "Update", JOptionPane.QUESTION_MESSAGE);

      if(input != null) {
        final AssetGroup newGroup = new AssetGroup(input, PortfolioProvider.getInstance().getPortfolio().getMaximumGroup() + 1);
        PortfolioProvider.getInstance().getPortfolio().addGroup(newGroup);
        tabbedPane.addTab(input, new AssetGroupPanel(newGroup));

        if(tabbedPane.getTabCount() > 1) {
          remGroupButton.setEnabled(true);
        }
      }
    }
  }
}
