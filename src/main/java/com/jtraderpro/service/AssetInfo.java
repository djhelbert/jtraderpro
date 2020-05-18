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

import java.util.Date;

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
  private Date exDate;
  private Date earningsAnnouncement;
  private Double avgVolume;
  
  public AssetInfo() {    
  }

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

  public Double getPreviousClose() {
    return previousClose;
  }

  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
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
  
  public Double getAvgVolume() {
    return avgVolume;
  }

  public void setAvgVolume(Double avgVolume) {
    this.avgVolume = avgVolume;
  }
}
