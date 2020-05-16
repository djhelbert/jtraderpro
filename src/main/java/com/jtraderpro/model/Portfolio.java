/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Portfolio
 * 
 * @author djhelbert
 */
public class Portfolio {

  private List<AssetGroup> groups = new ArrayList<>();

  public List<AssetGroup> getGroups() {
    return groups;
  }

  public void setGroups(List<AssetGroup> groups) {
    this.groups = groups;
  }

  public void addGroup(AssetGroup group) {
    groups.add(group);
  }

  public void reorder() {
    int count = 1;

    for(AssetGroup group : groups) {
      group.setOrder(count);
      count++;
    }
  }
}
