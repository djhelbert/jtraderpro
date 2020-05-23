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

import java.awt.Component;
import java.io.BufferedReader;
import java.io.InputStream;
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

  private final static ClassLoader classLoader = Util.class.getClassLoader();
  
  /**
   * Private Constructor
   */
  private Util() {  
  }
  
  /**
   * Get Image Icon
   *
   * @param name
   * @return
   */
  public static ImageIcon getImageIcon(String name) {
    final String imageResPath = "images/" + name;

    final URL iconURL = classLoader.getResource(imageResPath);

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
   * @param name
   * @return String
   * @throws Exception
   */
  public static String getFileText(String name) throws Exception {
    String text = "";

    ClassLoader cload = Util.class.getClassLoader();
    InputStream is = cload.getResourceAsStream(name);
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);

    String temp = br.readLine();

    while (temp != null) {
      text += temp + "\n";
      temp = br.readLine();
    }

    br.close();

    return text;
  }
}
