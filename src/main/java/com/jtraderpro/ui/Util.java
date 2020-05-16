/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.ui;

import java.awt.Component;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author djhelbert
 */
public class Util {

  	/**
	 * Get Image Icon
	 * 
	 * @param name
	 * @return
	 */
	public static ImageIcon getImageIcon(String name) {
		final String imageResPath = "images/" + name;
		ImageIcon icon = null;

		final ClassLoader cload = Util.class.getClassLoader();
		final URL iconURL = cload.getResource(imageResPath);

		if (iconURL != null) {
			icon = new ImageIcon(iconURL, name);
		}

		return icon;
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
    JOptionPane.showMessageDialog(c, label, title,
      JOptionPane.INFORMATION_MESSAGE);
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
}
