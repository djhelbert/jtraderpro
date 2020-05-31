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

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Value Table Cell Renderer
 */
public class ValueTableCellRenderer extends JLabel implements TableCellRenderer {

    private static final Color DARK_GREEN = new Color(51, 102, 0);
    private final static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    /**
     * Constructor
     */
    public ValueTableCellRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if(value instanceof Double) {
            setText(decimalFormat.format(value));
            final Double val = (Double) value;
            if (val == 0.00 || column == 2) {
                super.setForeground(Color.BLACK);
            } else if (val < 0.00 && column > 2) {
                super.setForeground(Color.RED);
            } else if (val > 0 && column > 2) {
                super.setForeground(DARK_GREEN);
            }
        } else {
            setText(value.toString());
        }

        return this;
    }

}