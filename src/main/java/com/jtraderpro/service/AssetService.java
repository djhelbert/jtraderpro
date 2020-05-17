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
        info.setName(stock.getName());
        
        return info;
      }
    } catch(Exception err) {
      err.printStackTrace();
    }

    return null; 
  }
}
