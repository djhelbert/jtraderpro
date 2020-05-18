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
public class Asset {

  private String symbol = "";
  private String name = "";
  private Integer order = 0;
  private List<Lot> lots = new ArrayList<>();

  public Asset() {
  }

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

  @Override
  public int hashCode() {
    return symbol.toUpperCase().hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o != null && o instanceof Asset) {
      final Asset a = (Asset) o;
      if (a.getSymbol().equalsIgnoreCase(symbol)) {
        return true;
      }
    }

    return false;
  }
}
