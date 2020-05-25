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

/**
 * Price Alert
 */
public class Alert {
    private Boolean above;
    private Double price;

    @SuppressWarnings("unused")
    public Alert() {
    }

    public Alert(Boolean above, Double price) {
        this.above = above;
        this.price = price;
    }

    public Boolean getAbove() {
        return above;
    }

    public void setAbove(Boolean above) {
        this.above = above;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
