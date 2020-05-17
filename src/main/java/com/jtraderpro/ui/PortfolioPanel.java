/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
  
  private JPanel buttonPanel = new JPanel();
  private JButton addGroupButton = new JButton("Group");
  private AssetGroupPanel assetGroupPanel = new AssetGroupPanel();
  private JTabbedPane tabbedPane = new JTabbedPane();
  
  public PortfolioPanel() {
    super();
    init();
  }

  private void init() {
    load();
    
    buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    addGroupButton.setIcon(Util.getImageIcon("add.png"));

    buttonPanel.add(addGroupButton);

    setLayout(new BorderLayout());
    
    add(buttonPanel, BorderLayout.PAGE_START);
    add(tabbedPane, BorderLayout.CENTER);
  }

  public void load() {
    tabbedPane.removeAll();

    for(AssetGroup group : PortfolioProvider.getInstance().getPortfolio().getGroups()) {
      tabbedPane.addTab(group.getName(), new AssetGroupPanel(group));
    }
  }
}
