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

    private final Asset modelAsset;

    /**
     * Constructor
     */
    public LotTableModel(Asset asset) {
        this.modelAsset = asset;
    }

    /**
     * Get Row Count
     *
     * @return int Rows
     */
    public int getRowCount() {
        return modelAsset.getLots().size();
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    /**
     * Get Value
     *
     * @param row Row
     * @param col Column
     * @return Object
     */
    public Object getValueAt(int row, int col) {
        final Lot lot = modelAsset.getLots().get(row);

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
        return col != 0 && col != 1;
    }

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
            modelAsset.getLots().get(row).setAmount(Integer.parseInt(value.toString()));
        } else if (col == 3) {
            modelAsset.getLots().get(row).setPrice(Double.parseDouble(value.toString()));
        } else {
            throw new IllegalArgumentException();
        }

        fireTableDataChanged();
    }

    public void removeLot(int row) {
        modelAsset.getLots().remove(row);
        modelAsset.reorder();

        fireTableDataChanged();
    }

    public void addLot() {
        if(modelAsset.getLots() == null) {
            modelAsset.setLots(new ArrayList<>());
        }

        modelAsset.getLots().add(new Lot(modelAsset.getLots().size(), 0, 0.00, new Date()));
        fireTableDataChanged();
    }
}
