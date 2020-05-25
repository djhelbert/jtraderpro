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
 * Asset Group
 *
 * @author djhelbert
 */
public class AssetGroup {

    public static final Integer MAX_SIZE = 40;

    private String name = "";
    private Integer order = 0;
    private List<Asset> assets = new ArrayList<>(MAX_SIZE);

    /**
     * Constructor
     */
    @SuppressWarnings("unused")
    public AssetGroup() {
    }

    /**
     * Constructor
     *
     * @param name Name
     * @param order Order
     */
    public AssetGroup(String name, Integer order) {
        this.name = name;
        this.order = order;
    }

    /**
     * Remove Asset
     *
     * @param symbol Symbol
     */
    public void removeAsset(String symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException();
        }

        int index = 0;

        for (Asset ass : assets) {
            if (ass.getSymbol().equalsIgnoreCase(symbol)) {
                assets.remove(index);
                return;
            }
            index++;
        }
    }

    /**
     * Add Asset
     *
     * @param asset Asset
     */
    public void addAsset(Asset asset) {
        if (assets.size() <= MAX_SIZE) {
            assets.add(asset);
        }
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

    @Override
    public int hashCode() {
        return order.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof AssetGroup) {
            final AssetGroup a = (AssetGroup) o;
            return a.getOrder().equals(order);
        }

        return false;
    }
}
