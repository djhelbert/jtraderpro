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
        info.setName(stock.getName());
        info.setMarketPrice(stock.getQuote().getPrice().doubleValue());
        info.setPreviousClose(stock.getQuote().getPreviousClose().doubleValue());
        info.setPercentChange(stock.getQuote().getChangeInPercent().doubleValue());
        info.setCurrency(stock.getCurrency());
        info.setExchange(stock.getStockExchange());
        info.setVolume(stock.getQuote().getVolume());
        info.setYearHigh(stock.getQuote().getYearHigh().doubleValue());
        info.setYearLow(stock.getQuote().getYearLow().doubleValue());
        info.setAvgVolume(stock.getQuote().getAvgVolume().doubleValue());
        info.setBid(stock.getQuote().getBid().doubleValue());
        info.setAsk(stock.getQuote().getAsk().doubleValue());
        info.setDayHigh(stock.getQuote().getDayHigh().doubleValue());
        info.setDayLow(stock.getQuote().getDayLow().doubleValue());
        info.setOpen(stock.getQuote().getOpen().doubleValue());

        if(stock.getStats().getEarningsAnnouncement() != null) {
          info.setEarningsAnnouncement(stock.getStats().getEarningsAnnouncement().getTime());
        }
        
        if(stock.getDividend() != null && stock.getDividend().getAnnualYieldPercent() != null) {
          info.setDividendYield(stock.getDividend().getAnnualYieldPercent().doubleValue());
        } else {
          info.setDividendYield(0.00);
        }

        if(stock.getDividend() != null && stock.getDividend().getExDate() != null) {
          info.setExDate(stock.getDividend().getExDate().getTime());
        }

        return info;
      }
    } catch(Exception err) {
      err.printStackTrace();
    }

    return null; 
  }
}
