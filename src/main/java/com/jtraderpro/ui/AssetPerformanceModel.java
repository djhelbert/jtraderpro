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
package com.jtraderpro.ui;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Asset Performance Table Model
 */
public class AssetPerformanceModel extends AbstractTableModel {

    private final static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private static final String[] headers = {"Symbol", "Name", "Price", "Change", "Dividend", "Year Low", "Year High", "Shares", "Value"};
    private final List<AssetPerformance> perfs;

    /**
     * Asset Performance Model
     *
     * @param perfs
     */
    public AssetPerformanceModel(List<AssetPerformance> perfs) {
        this.perfs = perfs;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public int getRowCount() {
        return perfs.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return perfs.get(row).getSymbol();
        } else if (col == 1) {
            return perfs.get(row).getName();
        } else if (col == 2) {
            return decimalFormat.format(perfs.get(row).getMarketPrice());
        } else if (col == 3) {
            return decimalFormat.format(perfs.get(row).getChange());
        } else if (col == 4) {
            return decimalFormat.format(perfs.get(row).getDividend());
        } else if (col == 5) {
            return decimalFormat.format(perfs.get(row).getYearLow());
        } else if (col == 6) {
            return decimalFormat.format(perfs.get(row).getYearHigh());
        } else if (col == 7) {
            return perfs.get(row).getShares().toString();
        } else if (col == 8) {
            return decimalFormat.format(perfs.get(row).getValue());
        }

        return perfs.get(row).getValue();
    }
}
