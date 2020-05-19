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
import java.awt.Component;
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

  private final static JMenuItem exitItem = new JMenuItem("Exit");
  private final static JMenuItem newItem = new JMenuItem("New");
  private final static JMenuItem saveItem = new JMenuItem("Save");
  private final static PortfolioPanel portfolioPanel = new PortfolioPanel();

  /**
   * Constructor
   */
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
    setContentPane(portfolioPanel);
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
      PortfolioProvider.getInstance().save();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  private void exitItemAction() {
    System.exit(0);
  }

  private void newItemAction() {
    PortfolioProvider.getInstance().getNewPortfolio();
    portfolioPanel.load();
  }

 /**
	 * Get Main Component
	 * 
	 * @return Component
	 */
	public static Component getMainComponent() {
		return portfolioPanel;
	}
  
}
