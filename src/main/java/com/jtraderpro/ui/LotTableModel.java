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

import com.jtraderpro.model.Asset;
import com.jtraderpro.model.Lot;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

/**
 * Team Table Model
 *
 * @author dhelbert
 */
public class LotTableModel extends AbstractTableModel {

    private static final String[] headers = {"Number", "Date", "Amount", "Price"};
    private Asset asset;

    /**
     * Constructor
     */
    public LotTableModel(Asset asset) {
        this.asset = asset;
    }

    /**
     * Refresh Data
     */
    public void refresh() {
        fireTableDataChanged();
    }

    /**
     * Get Lot at Row
     *
     * @param row
     * @return Lot
     */
    public Lot getTeam(int row) {
        return asset.getLots().get(row);
    }

    /**
     * Get Row Count
     *
     * @return int
     */
    public int getRowCount() {
        return asset.getLots().size();
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    /**
     * Get Value
     *
     * @param row
     * @param col
     * @return Object
     */
    public Object getValueAt(int row, int col) {
        final Lot lot = asset.getLots().get(row);

        if (col == 0) {
            return lot.getOrder() + 1;
        } else if (col == 1) {
            return lot.getDate();
        } else if (col == 2) {
            return lot.getAmount();
        } else if (col == 3) {
            return lot.getPrice();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Get Column Count
     *
     * @return int
     */
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0 || col == 1) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Class getColumnClass(int col) {
        if (col == 0) {
            return Integer.class;
        } else if (col == 1) {
            return Date.class;
        } else if (col == 2) {
            return Integer.class;
        } else {
            return Double.class;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col == 2) {
            asset.getLots().get(row).setAmount(Integer.parseInt(value.toString()));
        } else if (col == 3) {
            asset.getLots().get(row).setPrice(Double.parseDouble(value.toString()));
        } else {
            throw new IllegalArgumentException();
        }

        fireTableDataChanged();
    }

    public void removeLot(int row) {
        asset.getLots().remove(row);
        asset.reorder();

        fireTableDataChanged();
    }

    public void addLot() {
        if(asset.getLots() == null) {
            asset.setLots(new ArrayList<>());
        }

        asset.getLots().add(new Lot(asset.getLots().size(), 0, 0.00, new Date()));
        fireTableDataChanged();
    }
}
