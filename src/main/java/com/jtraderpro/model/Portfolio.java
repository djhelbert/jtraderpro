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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Portfolio
 *
 * @author djhelbert
 */
public class Portfolio {

  private Date date = new Date();
  private List<AssetGroup> groups = new ArrayList<>();

  /**
   * Constructor
   *
   */
  public Portfolio() {
  }

  @JsonIgnore
  public Integer getMaximumGroup() {
    Integer max = 0;
 
    for (AssetGroup group : groups) {
      if(group.getOrder() > max) {
        max = group.getOrder();
      }
    }

    return max;
  }
 
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

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

    for (AssetGroup group : groups) {
      group.setOrder(count);
      count++;
    }
  }
}
