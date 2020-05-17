/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Asset Service
 * 
 * @author djhelbert
 */
public class AssetService {
  
  private final static AssetService service = new AssetService();
  
  private AssetService() {  
  }
  
  public static AssetService getInstance() {
    return service;
  }

  public AssetInfo getAssetInfo(String symbol) {
    final AssetInfo info = new AssetInfo(symbol); 

    try {
      final Stock stock = YahooFinance.get(symbol);

      if(stock != null) {
        info.setMarketPrice(stock.getQuote().getPrice().doubleValue());
        info.setPreviousClose(stock.getQuote().getPreviousClose().doubleValue());
        info.setPercentChange(stock.getQuote().getChangeInPercent().doubleValue());
        info.setCurrency(stock.getCurrency());
        info.setExchange(stock.getStockExchange());
        info.setVolume(stock.getQuote().getVolume().doubleValue());

        return info;
      }
    } catch(Exception err) {
      err.printStackTrace();
    }

    return null; 
  }
}
