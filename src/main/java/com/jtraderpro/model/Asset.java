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
package com.jtraderpro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Asset
 *
 * @author djhelbert
 */
public class Asset implements Comparable {

  private String symbol = "";
  private String name = "";
  private Integer order = 0;
  private List<Lot> lots = new ArrayList<>();
  private Alert alert;

  /**
   * Constructor
   */
  @SuppressWarnings("unused")
  public Asset() {
  }

  /**
   * Constructor
   *
   * @param symbol Symbol
   * @param name Name
   * @param order Order
   */
  public Asset(String symbol, String name, Integer order) {
    this.symbol = symbol;
    this.name = name;
    this.order = order;
  }

  public List<Lot> getLots() {
    return lots;
  }

  public void setLots(List<Lot> lots) {
    this.lots = lots;
  }

  public void addLot(Lot lot) {
    lots.add(lot);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Alert getAlert() {
    return alert;
  }

  public void setAlert(Alert alert) {
    this.alert = alert;
  }

  @Override
  public int hashCode() {
    return symbol.toUpperCase().hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o != null && o instanceof Asset) {
      final Asset a = (Asset) o;
      return a.getSymbol().equalsIgnoreCase(symbol);
    }

    return false;
  }

  @Override
  public int compareTo(Object o) {
    if(o instanceof Asset) {
      return symbol.compareTo(((Asset)o).getSymbol());
    }

    return 0;
  }

  /**
   * Reorder Lots (used for removals)
   */
  public void reorder() {
    int count = 0;

    for (Lot lot : lots) {
      lot.setOrder(count);
      count++;
    }
  }

  /**
   * Get Current Asset Value
   *
   * @param marketPrice Market Price
   * @return Double
   */
  public Double getValue(Double marketPrice) {
    if(lots == null) {
      return null;
    } else if(lots.size() == 0) {
      return null;
    }

    double value = 0;

    for(Lot lot : lots) {
      value += (marketPrice - lot.getPrice()) * lot.getAmount();
    }

    return value;
  }
}
