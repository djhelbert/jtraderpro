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

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Utility Class
 * 
 * @author djhelbert
 */
public class Util {

  private final static ClassLoader CLASS_LOADER = Util.class.getClassLoader();
  
  /**
   * Private Constructor
   */
  private Util() {  
  }
  
  /**
   * Get Image Icon
   *
   * @param name Name
   * @return Image Icon
   */
  public static ImageIcon getImageIcon(String name) {
    final String imageResPath = "images/" + name;
    final URL iconURL = CLASS_LOADER.getResource(imageResPath);

    if (iconURL != null) {
      return new ImageIcon(iconURL, name);
    } else {
      return null;
    }
  }

  /**
   * Show Error Dialog
   *
   * @param c Component
   * @param message Message String
   * @param title Dialog Title
   */
  public static void showInfo(Component c, String message, String title) {
    final JLabel label = new JLabel(message);
    JOptionPane.showMessageDialog(c, label, title, JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Show Plain Dialog
   *
   * @param c Component
   * @param m Component
   * @param title Dialog Title
   */
  public static void showInfo(Component c, Component m, String title) {
    JOptionPane.showMessageDialog(c, m, title, JOptionPane.PLAIN_MESSAGE);
  }


  /**
   * Get File Text
   *
   * @param name Name
   * @return String File Text as String
   * @throws Exception Exception
   */
  public static String getFileText(String name) throws Exception {
    final StringBuilder text = new StringBuilder();
    final InputStreamReader isr = new InputStreamReader(CLASS_LOADER.getResourceAsStream(name));
    final BufferedReader br = new BufferedReader(isr);

    String temp = br.readLine();

    while (temp != null) {
      text.append(temp);
      text.append('\n');
      temp = br.readLine();
    }

    br.close();

    return text.toString();
  }

  /**
   * Center
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
