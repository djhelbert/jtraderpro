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

import java.util.Calendar;
import java.util.List;
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

  private final static AssetService service = new AssetService();

  private AssetService() {
  }

  public static AssetService getInstance() {
    return service;
  }

  public AssetInfo getAssetInfo(String symbol) {
    return getAssetInfo(symbol, false);
  }

  public AssetInfo getAssetInfo(String symbol, boolean historical) {
    final AssetInfo info = new AssetInfo(symbol);

    try {
      final Stock stock = YahooFinance.get(symbol, historical);

      if (historical) {
        final Calendar from = Calendar.getInstance();
        from.add(Calendar.DATE, -5);

        final List<HistoricalQuote> histQuotes = stock.getHistory(from, Interval.DAILY);

        histQuotes.stream().map((q) -> {
          AssetQuote aq = new AssetQuote();
          aq.setClose(q.getClose().doubleValue());
          aq.setDate(q.getDate().getTime());
          aq.setHigh(q.getHigh().doubleValue());
          aq.setLow(q.getLow().doubleValue());
          aq.setVolume(q.getVolume());
          return aq;
        }).forEachOrdered((aq) -> {
          info.getAssetQuotes().add(aq);
        });
      }

      if (stock != null) {
        info.setName(stock.getName());
        info.setMarketPrice(stock.getQuote().getPrice().doubleValue());
        info.setPreviousClose(stock.getQuote().getPreviousClose().doubleValue());
        info.setPercentChange(stock.getQuote().getChangeInPercent().doubleValue());
        info.setCurrency(stock.getCurrency());
        info.setExchange(stock.getStockExchange());
        info.setVolume(stock.getQuote().getVolume());
        info.setYearHigh(stock.getQuote().getYearHigh().doubleValue());
        info.setYearLow(stock.getQuote().getYearLow().doubleValue());
        info.setAvgVolume(stock.getQuote().getAvgVolume());
        info.setBid(stock.getQuote().getBid().doubleValue());
        info.setAsk(stock.getQuote().getAsk().doubleValue());
        info.setDayHigh(stock.getQuote().getDayHigh().doubleValue());
        info.setDayLow(stock.getQuote().getDayLow().doubleValue());
        info.setOpen(stock.getQuote().getOpen().doubleValue());
        info.setMarketCap(stock.getStats().getMarketCap().doubleValue());
        info.setStockExchange(stock.getStockExchange());
        info.setAskSize(stock.getQuote().getAskSize());
        info.setBidSize(stock.getQuote().getBidSize());

        if(stock.getStats().getEps() != null) {
          info.setEps(stock.getStats().getEps().doubleValue());
        }

        if(stock.getStats().getPe() != null) {
          info.setPe(stock.getStats().getPe().doubleValue());
        }

        if (stock.getStats().getPriceBook() != null) {
          info.setPriceBook(stock.getStats().getPriceBook().doubleValue());
        }

        if (stock.getStats().getPriceSales() != null) {
          info.setPriceSales(stock.getStats().getPriceSales().doubleValue());
        }

        if (stock.getStats().getRevenue() != null) {
          info.setRevenue(stock.getStats().getRevenue().doubleValue());
        }

        if (stock.getStats().getPeg() != null) {
          info.setPeg(stock.getStats().getPeg().doubleValue());
        }

        if (stock.getStats().getEarningsAnnouncement() != null) {
          info.setEarningsAnnouncement(stock.getStats().getEarningsAnnouncement().getTime());
        }

        if (stock.getDividend() != null && stock.getDividend().getAnnualYieldPercent() != null) {
          info.setDividendYield(stock.getDividend().getAnnualYieldPercent().doubleValue());
        } else {
          info.setDividendYield(0.00);
        }

        if (stock.getDividend() != null && stock.getDividend().getPayDate() != null) {
          info.setExDate(stock.getDividend().getPayDate().getTime());
        }

        return info;
      }
    } catch (Exception err) {
      err.printStackTrace();
    }

    return null;
  }
}
