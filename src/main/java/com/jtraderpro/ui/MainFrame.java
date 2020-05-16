/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.ui;

import com.jtraderpro.PortfolioProvider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Main Frame
 *
 * @author djhelbert
 */
public class MainFrame extends JFrame implements ActionListener {

  /**
   * Exit Item
   */
  private final static JMenuItem exitItem = new JMenuItem("Exit");

  /**
   * New Item
   */
  private final static JMenuItem newItem = new JMenuItem("New");

  /**
   * Save Item
   */
  private final static JMenuItem saveItem = new JMenuItem("Save");

  /**
   * Portfolio Provider
   */
  private final PortfolioProvider provider = new PortfolioProvider();
  
  public MainFrame() {
    super("JTraderPro");
    init();
  }

  private void init() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final JMenuBar menuBar = new JMenuBar();
    final JMenu menu = new JMenu("File");

    newItem.setIcon(Util.getImageIcon("file.png"));
    saveItem.setIcon(Util.getImageIcon("save.png"));
    exitItem.setIcon(Util.getImageIcon("exit.png"));

    menu.add(newItem);
    menu.add(saveItem);
    menu.addSeparator();
    menu.add(exitItem);
    menuBar.add(menu);

    saveItem.addActionListener(this);
    newItem.addActionListener(this);
    exitItem.addActionListener(this);

    setJMenuBar(menuBar);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(exitItem)) {
      exitItemAction();
    }
    if (e.getSource().equals(newItem)) {
      newItemAction();
    }
    if (e.getSource().equals(saveItem)) {
      saveItemAction();
    }
  }

  private void saveItemAction() {
    try {
      provider.save();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  private void exitItemAction() {
    System.exit(0);
  }

  private void newItemAction() {
    provider.getNewPortfolio();
  }
}
