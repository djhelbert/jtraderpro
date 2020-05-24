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
  private static final JButton sortButton = new JButton("Sort");
  private static final JTabbedPane tabbedPane = new JTabbedPane();
  private static final DetailPanel detailPanel = new DetailPanel(PortfolioProvider.getInstance().getPortfolio().getDefaultAsset().getSymbol());
  private static final JLabel djiLabel = new JLabel();
  private static final JLabel ixicLabel = new JLabel();
  private static final JLabel spxLabel = new JLabel();
  private static final JLabel hkdowLabel = new JLabel();
  private static final JLabel n225Label = new JLabel();
  private static final JLabel cadowLabel = new JLabel();
  private static final JLabel ftseLabel = new JLabel();
  private static final JLabel dedowLabel = new JLabel();
  private static final JLabel djshLabel = new JLabel();
  private static final Logger logger = LoggerFactory.getLogger(PortfolioPanel.class);
  private static final String DJI = "^DJI";
  private static final String IXIC = "^IXIC";
  private static final String SPX = "^SPX";
  private static final String HKDOW = "^HKDOW";
  private static final String N225 = "^N225";
  private static final String CADOW = "^CADOW";
  private static final String FTSE = "^FTSE";
  private static final String DEDOW = "^DEDOW";
  private static final String DJSH = "^DJSH";
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

    djiLabel.setToolTipText("Dow Jones Indus.");
    ixicLabel.setToolTipText("Nasdaq");
    spxLabel.setToolTipText("S&P 500");
    cadowLabel.setToolTipText("Dow Jones Canada");
    ftseLabel.setToolTipText("FTSE 100");
    dedowLabel.setToolTipText("Dow Jones Germany");
    hkdowLabel.setToolTipText("Dow Jones Hong Kong");
    djshLabel.setToolTipText("Dow Jones Shanghai");
    n225Label.setToolTipText("Nikkei 225");
    buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    saveButton.addActionListener(this);
    saveButton.setIcon(Util.getImageIcon("save.png"));
    addGroupButton.addActionListener(this);
    addGroupButton.setIcon(Util.getImageIcon("add.png"));
    remGroupButton.addActionListener(this);
    remGroupButton.setIcon(Util.getImageIcon("delete.png"));
    sortButton.addActionListener(this);
    sortButton.setIcon(Util.getImageIcon("sort.png"));
    buttonPanel.add(saveButton);
    buttonPanel.add(addGroupButton);
    buttonPanel.add(remGroupButton);
    buttonPanel.add(sortButton);
    indexPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    indexPanel.add(new JLabel("^DJI"));
    indexPanel.add(djiLabel);
    indexPanel.add(new JLabel("^IXIC"));
    indexPanel.add(ixicLabel);
    indexPanel.add(new JLabel("^SPX"));
    indexPanel.add(spxLabel);
    indexPanel.add(new JLabel("^CADOW"));
    indexPanel.add(cadowLabel);
    indexPanel.add(new JLabel("^FTSE"));
    indexPanel.add(ftseLabel);
    indexPanel.add(new JLabel("^DEDOW"));
    indexPanel.add(dedowLabel);
    indexPanel.add(new JLabel("^HKDOW"));
    indexPanel.add(hkdowLabel );
    indexPanel.add(new JLabel("^DJSH"));
    indexPanel.add(djshLabel);
    indexPanel.add(new JLabel("^N225"));
    indexPanel.add(n225Label);

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
    final AssetInfo infoCadow = AssetService.getInstance().getAssetInfo(CADOW);
    final AssetInfo infoFtse = AssetService.getInstance().getAssetInfo(FTSE);
    final AssetInfo infoDedow = AssetService.getInstance().getAssetInfo(DEDOW);
    final AssetInfo infoN225 = AssetService.getInstance().getAssetInfo(N225);
    final AssetInfo infoHkdow = AssetService.getInstance().getAssetInfo(HKDOW);
    final AssetInfo infoDjsh = AssetService.getInstance().getAssetInfo(DJSH);

    updateIndex(djiLabel, infoDji);
    updateIndex(ixicLabel, infoIxic);
    updateIndex(spxLabel, infoSpx);
    updateIndex(cadowLabel, infoCadow);
    updateIndex(ftseLabel, infoFtse);
    updateIndex(dedowLabel, infoDedow);
    updateIndex(hkdowLabel, infoHkdow);
    updateIndex(n225Label, infoN225);
    updateIndex(djshLabel, infoDjsh);
  }

  private void updateIndex(JLabel label, AssetInfo info) {
    if(info != null) {
      label.setText(info.getPercentChange().toString() + "%");

      if(info.getPercentChange() < 0.00) {
        label.setForeground(Color.red);
      } else {
        label.setForeground(DARK_GREEN);
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
    } else if(e.getSource().equals(sortButton)) {
      final AssetGroupPanel agPanel = (AssetGroupPanel)tabbedPane.getSelectedComponent();
      agPanel.sort();
    } else if(e.getSource().equals(remGroupButton)) {
      if(tabbedPane.getTabCount() > 1) {
        int index = tabbedPane.getSelectedIndex();
        final AssetGroupPanel agPanel = (AssetGroupPanel)tabbedPane.getSelectedComponent();
        agPanel.shutdown();
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

  /**
   * Update Indexes Task
   */
  private class UpdateIndexesTask implements Runnable {
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
