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

import com.jtraderpro.Main;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 * Performance Dialog
 */
public class AssetPerformanceDialog extends JDialog implements ActionListener, WindowListener {

    private final JButton okButton = new JButton("Ok");
    private final JPanel lowerPanel = new JPanel();
    private final AssetPerformanceModel model;
    private final JTable table;
    private final JScrollPane scroller;

    /**
     * Constructor
     *
     * @param perfs Performance List
     */
    public AssetPerformanceDialog(List<AssetPerformance> perfs) {
        super(Main.getMainFrame());
        setTitle("Performance");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        addWindowListener(this);

        model = new AssetPerformanceModel(perfs);
        table = new JTable(model);
        scroller = new JScrollPane(table);

        init();
    }

    /**
     * Size Column
     *
     * @param table Table
     * @param col Column Number
     * @param size Size
     */
    private void sizeTableColumns(JTable table, int col, int size) {
        final TableColumn column = table.getColumnModel().getColumn(col);
        column.setPreferredWidth(size);
    }

    /**
     * Initialize Dialog
     */
    private void init() {
        okButton.addActionListener(this);
        okButton.setIcon(Util.getImageIcon("check.png"));

        lowerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        lowerPanel.add(okButton);

        final Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(scroller, BorderLayout.CENTER);
        cont.add(lowerPanel, BorderLayout.PAGE_END);

        sizeTableColumns(table, 0, 70);
        sizeTableColumns(table, 1, 150);
        sizeTableColumns(table, 2, 70);
        sizeTableColumns(table, 3, 70);
        sizeTableColumns(table, 4, 70);
        sizeTableColumns(table, 5, 70);
        sizeTableColumns(table, 6, 70);
        sizeTableColumns(table, 7, 70);
        sizeTableColumns(table, 8, 70);

        table.setDefaultRenderer(Double.class, new ValueTableCellRenderer());

        setPreferredSize(new Dimension(800,700));

        pack();
        Util.centerComponent(this);
    }

    /**
     * Close Dialog
     */
    private void closeDialog() {
        // Close
        setVisible(false);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(okButton)) {
            closeDialog();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
