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
package com.jtraderpro;

import com.jtraderpro.ui.MainFrame;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.*;

import com.jtraderpro.ui.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main
 *
 * @author djhelbert
 */
public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  private static MainFrame frame;

  /**
   * Main
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    init();

    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch(Exception err) {
      logger.error("UI Mananger Error", err);
    }

    frame = new MainFrame();
    frame.setSize(1000, 800);
    frame.setResizable(true);
    frame.setVisible(true);
    frame.setIconImage(Util.getImageIcon("stocks.png").getImage());
    centerComponent(frame);
  }

  private static void init() {
    if (!PortfolioProvider.getInstance().defaultExists()) {
      PortfolioProvider.getInstance().getNewPortfolio();

      try {
        PortfolioProvider.getInstance().save();
      } catch (IOException err) {
        logger.error("Portfolio Provider", err);
      }
    } else {
      try {
        PortfolioProvider.getInstance().load();
      } catch (IOException err) {
        logger.error("Main Error", err);
      }
    }
  }

  /**
   * Method to center component on screen.
   *
   * @param comp Component
   */
  public static void centerComponent(Component comp) {
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final Dimension size = comp.getSize();
    screenSize.height = screenSize.height / 2;
    screenSize.width = screenSize.width / 2;
    size.height = size.height / 2;
    size.width = size.width / 2;
    int y = screenSize.height - size.height;
    int x = screenSize.width - size.width;
    comp.setLocation(x, y);
  }

  public static JFrame getMainFrame() {
    return frame;
  }
}
