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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Asset Info
 * @author djhelbert
 */
public class AssetInfo {
  private String symbol;
  private String name;
  private String currency;
  private String type;
  private String exchange;
  private Double marketPrice;
  private Double previousClose;
  private Double percentChange;
  private Long volume;
  private Double yearHigh;
  private Double yearLow;
  private Double bid;
  private Double ask;
  private Double open;
  private Double dayHigh;
  private Double dayLow;
  private Double dividendYield;
  private Double annualYield;
  private Date exDate;
  private Date earningsAnnouncement;
  private Long avgVolume;
  private Double eps;
  private Double peg;
  private Double priceBook;
  private Double priceSales;
  private Double revenue;
  private Double marketCap;
  private Double pe;
  private String stockExchange;
  private Long askSize;
  private Long bidSize;
  private Double roc;
  private Double rsi;
  private Double fiftyDayAvg;

  private List<AssetQuote> assetQuotes = new ArrayList<>();

  public AssetInfo(final String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public Double getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(Double marketPrice) {
    this.marketPrice = marketPrice;
  }

  public Double getPriceChange() {
    return marketPrice - open;
  }

  public Double getPreviousClose() {
    return previousClose;
  }

  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
  }

  public Double getAnnualYield() {
    return annualYield;
  }

  public void setAnnualYield(Double annualYield) {
    this.annualYield = annualYield;
  }

  public Double getPercentChange() {
    return percentChange;
  }

  public void setPercentChange(Double percentChange) {
    this.percentChange = percentChange;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }

  public Double getYearHigh() {
    return yearHigh;
  }

  public void setYearHigh(Double yearHigh) {
    this.yearHigh = yearHigh;
  }

  public Double getYearLow() {
    return yearLow;
  }

  public void setYearLow(Double yearLow) {
    this.yearLow = yearLow;
  }

  public Double getBid() {
    return bid;
  }

  public void setBid(Double bid) {
    this.bid = bid;
  }

  public Double getAsk() {
    return ask;
  }

  public void setAsk(Double ask) {
    this.ask = ask;
  }

  public Double getOpen() {
    return open;
  }

  public void setOpen(Double open) {
    this.open = open;
  }

  public Double getDayHigh() {
    return dayHigh;
  }

  public void setDayHigh(Double dayHigh) {
    this.dayHigh = dayHigh;
  }

  public Double getDayLow() {
    return dayLow;
  }

  public void setDayLow(Double dayLow) {
    this.dayLow = dayLow;
  }

  public Double getDividendYield() {
    return dividendYield;
  }

  public void setDividendYield(Double dividendYield) {
    this.dividendYield = dividendYield;
  }

  public Date getExDate() {
    return exDate;
  }

  public void setExDate(Date exDate) {
    this.exDate = exDate;
  }

  public Date getEarningsAnnouncement() {
    return earningsAnnouncement;
  }

  public void setEarningsAnnouncement(Date earningsAnnouncement) {
    this.earningsAnnouncement = earningsAnnouncement;
  }
  
  public Long getAvgVolume() {
    return avgVolume;
  }

  public void setAvgVolume(Long avgVolume) {
    this.avgVolume = avgVolume;
  }

  public Double getEps() {
    return eps;
  }

  public void setEps(Double eps) {
    this.eps = eps;
  }

  public Double getPeg() {
    return peg;
  }

  public void setPeg(Double peg) {
    this.peg = peg;
  }

  public Double getPriceBook() {
    return priceBook;
  }

  public void setPriceBook(Double priceBook) {
    this.priceBook = priceBook;
  }

  public Double getPriceSales() {
    return priceSales;
  }

  public void setPriceSales(Double priceSales) {
    this.priceSales = priceSales;
  }

  public Double getRevenue() {
    return revenue;
  }

  public void setRevenue(Double revenue) {
    this.revenue = revenue;
  }

  public Double getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(Double marketCap) {
    this.marketCap = marketCap;
  }

  public Double getPe() {
    return pe;
  }

  public void setPe(Double pe) {
    this.pe = pe;
  }

  public String getStockExchange() {
    return stockExchange;
  }

  public void setStockExchange(String stockExchange) {
    this.stockExchange = stockExchange;
  }

  public Long getAskSize() {
    return askSize;
  }

  public void setAskSize(Long askSize) {
    this.askSize = askSize;
  }

  public Long getBidSize() {
    return bidSize;
  }

  public void setBidSize(Long bidSize) {
    this.bidSize = bidSize;
  }

  public Double getFiftyDayAvg() {
    return fiftyDayAvg;
  }

  public void setFiftyDayAvg(Double fiftyDayAvg) {
    this.fiftyDayAvg = fiftyDayAvg;
  }

  public Double getRoc() {
    return roc;
  }

  public Double getRsi() {
    return rsi;
  }

  public void updateRsi() {
    if(assetQuotes.size() < 14) {
      rsi = 0.0;
      return;
    }

    double sumGains = 0.0d;
    double sumLosses = 0.0d;
    double currentGain = 0.0d;
    double currentLoss = 0.0d;

    if(getPriceChange() > 0) {
      currentGain += getPriceChange();
    } else {
      currentLoss += Math.abs(getPriceChange());
    }

    double avgGain = 0.0d;
    double avgLoss = 0.0d;

    for(int i = assetQuotes.size() - 14; i< assetQuotes.size(); i++) {
      double change = assetQuotes.get(i).getClose() - assetQuotes.get(i).getOpen();

      if(change > 0.0) {
        sumGains += change;
      } else {
        sumLosses += Math.abs(change);
      }
    }

    avgGain = sumGains / 14.0d;
    avgLoss = sumLosses / 14.0d;

    if(avgLoss <= 0) {
      rsi = 100.0;
      return;
    }

    rsi = 100 - (100 / (1 + ((((avgGain * 13.0) + currentGain)) / 14.0) / ((((avgLoss * 13.0) + currentLoss)) / 14.0)));
  }

  public void updateRoc() {
    if(assetQuotes.size() > 0) {
      roc = ((assetQuotes.get(assetQuotes.size()-1).getClose() - assetQuotes.get(0).getClose()) / assetQuotes.get(0).getClose()) * 100.0;
    }
  }

  public List<AssetQuote> getAssetQuotes() {
    return assetQuotes;
  }

  public void setAssetQuotes(List<AssetQuote> assetQuotes) {
    this.assetQuotes = assetQuotes;
  }
}
