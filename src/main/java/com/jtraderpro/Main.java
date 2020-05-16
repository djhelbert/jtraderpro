/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro;

import com.jtraderpro.ui.MainFrame;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

/**
 * Main
 *
 * @author djhelbert
 */
public class Main {

  /**
   * Main
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    init();
    final MainFrame frame = new MainFrame();
    frame.setVisible(true);
    centerComponent(frame);
  }

  private static void init() {
    final PortfolioProvider provider = new PortfolioProvider();

    if (!provider.defaultExists()) {
      provider.getNewPortfolio();

      try {
        provider.save();
      } catch (IOException err) {
        err.printStackTrace();
      }
    } else {
      try {
        provider.load();
      } catch (IOException err) {
        err.printStackTrace();
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

}
