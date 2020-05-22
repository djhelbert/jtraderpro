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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * Asset Service
 *
 * @author djhelbert
 */
public class AssetService {

  private static final AssetService service = new AssetService();
  private static final Logger logger = LoggerFactory.getLogger(AssetService.class);

  /**
   * Private Constructor
   */
  private AssetService() {
  }

  /**
   * Get Singleton Instance
   * @return 
   */
  public static AssetService getInstance() {
    return service;
  }

  /**
   * Get Asset Information with no History
   * @param symbol
   * @return 
   */
  public AssetInfo getAssetInfo(String symbol) {
    return getAssetInfo(symbol, false);
  }

  /**
   * Get Asset Information
   * 
   * @param symbol
   * @param historical
   * @return 
   */
  public AssetInfo getAssetInfo(String symbol, boolean historical) {
    final AssetInfo info = new AssetInfo(symbol);

    try {
      final Stock stock = YahooFinance.get(symbol, historical);

      if (stock != null && historical) {
        final Calendar from = Calendar.getInstance();
        from.add(Calendar.DATE, -30);

        final List<HistoricalQuote> histQuotes = stock.getHistory(from, Interval.DAILY);

        histQuotes.stream().map((q) -> {
          AssetQuote aq = new AssetQuote();
          aq.setClose(getDouble(q.getClose()));
          aq.setDate(q.getDate().getTime());
          aq.setHigh(getDouble(q.getHigh()));
          aq.setLow(getDouble(q.getLow()));
          aq.setVolume(q.getVolume());
          return aq;
        }).forEachOrdered((aq) -> {
          info.getAssetQuotes().add(aq);
        });
      }

      if (stock != null) {
        info.setName(stock.getName());
        info.setMarketPrice(getDouble(stock.getQuote().getPrice()));
        info.setPreviousClose(getDouble(stock.getQuote().getPreviousClose()));
        info.setPercentChange(getDouble(stock.getQuote().getChangeInPercent()));
        info.setCurrency(stock.getCurrency());
        info.setExchange(stock.getStockExchange());
        info.setVolume(stock.getQuote().getVolume());
        info.setYearHigh(getDouble(stock.getQuote().getYearHigh()));
        info.setYearLow(getDouble(stock.getQuote().getYearLow()));
        info.setAvgVolume(stock.getQuote().getAvgVolume());
        info.setBid(getDouble(stock.getQuote().getBid()));
        info.setAsk(getDouble(stock.getQuote().getAsk()));
        info.setDayHigh(getDouble(stock.getQuote().getDayHigh()));
        info.setDayLow(getDouble(stock.getQuote().getDayLow()));
        info.setOpen(getDouble(stock.getQuote().getOpen()));
        info.setMarketCap(getDouble(stock.getStats().getMarketCap()));
        info.setStockExchange(stock.getStockExchange());
        info.setAskSize(stock.getQuote().getAskSize());
        info.setBidSize(stock.getQuote().getBidSize());

        if(stock.getStats().getEps() != null) {
          info.setEps(getDouble(stock.getStats().getEps()));
        }

        if(stock.getStats().getPe() != null) {
          info.setPe(getDouble(stock.getStats().getPe()));
        }

        if (stock.getStats().getPriceBook() != null) {
          info.setPriceBook(getDouble(stock.getStats().getPriceBook()));
        }

        if (stock.getStats().getPriceSales() != null) {
          info.setPriceSales(getDouble(stock.getStats().getPriceSales()));
        }

        if (stock.getStats().getRevenue() != null) {
          info.setRevenue(getDouble(stock.getStats().getRevenue()));
        }

        if (stock.getStats().getPeg() != null) {
          info.setPeg(getDouble(stock.getStats().getPeg()));
        }

        if (stock.getStats().getEarningsAnnouncement() != null) {
          info.setEarningsAnnouncement(stock.getStats().getEarningsAnnouncement().getTime());
        }

        if (stock.getDividend() != null && stock.getDividend().getAnnualYieldPercent() != null) {
          info.setDividendYield(getDouble(stock.getDividend().getAnnualYieldPercent()));
        } else {
          info.setDividendYield(0.00);
        }

        if (stock.getDividend() != null && stock.getDividend().getPayDate() != null) {
          info.setExDate(stock.getDividend().getPayDate().getTime());
        }

        return info;
      }
    } catch (IOException e) {
      logger.error("AssetService", e);
    }

    return null;
  }

  private double getDouble(BigDecimal big) {
    if(big == null) {
      return 0.0d;
    } else {
      return big.doubleValue();
    }
  }
}
