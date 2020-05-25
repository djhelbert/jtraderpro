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

import java.util.Date;

/**
 * Lot
 *
 * @author djhelbert
 */
public class Lot {

  private Integer order;
  private Integer amount = 0;
  private Double price = 0.00;
  private Date date = new Date();

  /**
   * Constructor
   */
  public Lot() {
  }

  /**
   * Constructor
   *
   * @param order
   * @param amount
   * @param price
   * @param date
   */
  public Lot(Integer order, Integer amount, Double price, Date date) {
    this.amount = amount;
    this.date = date;
    this.price = price;
    this.order = order;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  @Override
  public int hashCode() {
    return order.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o != null && o instanceof Lot) {
      final Lot a = (Lot) o;
      if (a.getOrder().equals(order)) {
        return true;
      }
    }

    return false;
  }
}
