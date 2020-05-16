/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Asset Group
 *
 * @author djhelbert
 */
public class AssetGroup {

  private String name = "";
  private Integer order = 0;
  private List<Asset> assets = new ArrayList<>();

  public AssetGroup() {
  }

  public AssetGroup(String name, Integer order) {
    this.name = name;
    this.order = order;
  }

  public void reorder() {
    int count = 1;

    for (Asset asset : assets) {
      asset.setOrder(count);
      count++;
    }
  }

  public void removeAsset(Asset asset) {
    assets.remove(asset);
  }

  public void addAsset(Asset asset) {
    assets.add(asset);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public List<Asset> getAssets() {
    return assets;
  }

  public void setAssets(List<Asset> assets) {
    this.assets = assets;
  }
}
