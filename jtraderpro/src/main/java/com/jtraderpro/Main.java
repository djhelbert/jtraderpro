/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro;

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
  }

  private static void init() {
    final PortfolioProvider provider = new PortfolioProvider();

    if( !provider.defaultExists() ) {
      provider.getNewPortfolio();

      try {
        provider.save();
      } catch(IOException err) {
        err.printStackTrace();
      }
    } else {
      System.out.println("Default file exists.");
    }
  }
}
