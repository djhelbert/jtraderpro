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
import com.jtraderpro.service.AssetInfo;
import com.jtraderpro.service.AssetService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
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

  private static final JPanel indexPanel = new JPanel();
  private static final JPanel buttonPanel = new JPanel();
  private static final JButton addGroupButton = new JButton("Group");
  private static final JButton remGroupButton = new JButton("Group");
  private static final JButton saveButton = new JButton("Save");
  private static final JTabbedPane tabbedPane = new JTabbedPane();
  private static final DetailPanel detailPanel = new DetailPanel();
  private static final JLabel djiLabel = new JLabel();
  private static final JLabel ixicLabel = new JLabel();
  private static final JLabel spxLabel = new JLabel();
  private static final Logger logger = LoggerFactory.getLogger(PortfolioPanel.class);
  private static final String DJI = "^DJI";
  private static final String IXIC = "^IXIC";
  private static final String SPX = "^SPX";
  private static final Color DARK_GREEN = new Color(51, 102, 0);
  private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

  public PortfolioPanel() {
    super();
    init();
    updateIndexes();
    executor.scheduleAtFixedRate(new UpdateIndexesTask(), 60, 60, TimeUnit.SECONDS);
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
    indexPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    indexPanel.add(new JLabel("^DJI"));
    indexPanel.add(djiLabel);
    indexPanel.add(new JLabel("^IXIC"));
    indexPanel.add(ixicLabel);
    indexPanel.add(new JLabel("^SPX"));
    indexPanel.add(spxLabel);
    indexPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
  
    setBackground(Color.white);
    setLayout(new BorderLayout());
    
    add(buttonPanel, BorderLayout.PAGE_START);
    add(tabbedPane, BorderLayout.CENTER);
    add(detailPanel, BorderLayout.LINE_END);
    add(indexPanel, BorderLayout.PAGE_END);
  }

  public static DetailPanel getDetailPanel() {
    return detailPanel;
  }

  private void updateIndexes() {
    final AssetInfo infoDji = AssetService.getInstance().getAssetInfo(DJI);
    final AssetInfo infoIxic = AssetService.getInstance().getAssetInfo(IXIC);
    final AssetInfo infoSpx = AssetService.getInstance().getAssetInfo(SPX);
 
    if(infoDji != null) {
      djiLabel.setText(infoDji.getPercentChange().toString() + "%");
      
      if(infoDji.getPercentChange() < 0.00) {
        djiLabel.setForeground(Color.red);
      } else {
        djiLabel.setForeground(DARK_GREEN);
      }
    }

    if(infoIxic != null) {
      ixicLabel.setText(infoIxic.getPercentChange().toString() + "%");

      if(infoIxic.getPercentChange() < 0.00) {
        ixicLabel.setForeground(Color.red);
      } else {
        ixicLabel.setForeground(DARK_GREEN);
      }
    }

    if(infoSpx != null) {
      spxLabel.setText(infoSpx.getPercentChange().toString() + "%");

      if(infoSpx.getPercentChange() < 0.00) {
        spxLabel.setForeground(Color.red);
      } else {
        spxLabel.setForeground(DARK_GREEN);
      }
    }
  }
  
  public void load() {
    tabbedPane.removeAll();

    PortfolioProvider.getInstance().getPortfolio().getGroups().forEach((group) -> {
      tabbedPane.addTab(group.getName(), new AssetGroupPanel(group));
    });

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
      } catch(IOException err) {
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
  
  private class UpdateIndexesTask implements Runnable {
    public UpdateIndexesTask() {
    }

    @Override
    public void run() {
      try {
        updateIndexes();
      } catch (Exception e) {
        logger.error("Update Indexes Task", e);
      }
    }
  }
}
