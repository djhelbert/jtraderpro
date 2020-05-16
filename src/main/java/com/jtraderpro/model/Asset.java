/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.model;

import java.util.Date;

/**
 * Asset
 *
 * @author djhelbert
 */
public class Asset {

  private String symbol = "";
  private String name = "";
  private Integer order = 0;
  private Integer amount = 0;
  private Double price = 0.00;
  private Date date = new Date();

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

  @Override
  public int hashCode() {
    return symbol.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o != null) {
      if (o instanceof Asset) {
        final Asset a = (Asset) o;
        if (a.getSymbol().equalsIgnoreCase(symbol)) {
          return true;
        }
      }
    }

    return false;
  }
}
