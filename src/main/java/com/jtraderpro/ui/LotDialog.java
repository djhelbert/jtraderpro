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
import com.jtraderpro.model.Asset;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Lot Dialog
 */
public class LotDialog extends JDialog implements ActionListener, WindowListener {

    private final JButton addButton = new JButton("Add Lot");
    private final JButton deleteButton = new JButton("Delete Lot");
    private JButton okButton = new JButton("Ok");
    private final JPanel upperPanel = new JPanel();
    private final JPanel lowerPanel = new JPanel();
    private final LotTableModel model;
    private JTable table;
    private JScrollPane scroller;
    private Asset asset;
    private AssetPanel assetPanel;

    /**
     * Constructor
     *
     * @param asset
     * @param assetPanel
     */
    public LotDialog(Asset asset, AssetPanel assetPanel) {
        super(Main.getMainFrame());
        setTitle(asset.getSymbol() + " Lots");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        addWindowListener(this);

        this.asset = asset;
        this.assetPanel = assetPanel;

        if (asset.getLots() != null && asset.getLots().size() > 0) {
            deleteButton.setEnabled(true);
        } else {
            deleteButton.setEnabled(false);
        }

        model = new LotTableModel(asset);
        table = new JTable(model);
        scroller = new JScrollPane(table);

        init();
    }

    /**
     * Initialize Dialog
     */
    private void init() {
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        okButton.addActionListener(this);
        addButton.setIcon(Util.getImageIcon("add.png"));
        deleteButton.setIcon(Util.getImageIcon("minus.png"));
        okButton.setIcon(Util.getImageIcon("check.png"));

        upperPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        upperPanel.add(addButton);
        upperPanel.add(deleteButton);
        lowerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        lowerPanel.add(okButton);

        sizeTableColumns(table, 0, 25);
        sizeTableColumns(table, 3, 25);

        final Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(upperPanel, BorderLayout.PAGE_START);
        cont.add(scroller, BorderLayout.CENTER);
        cont.add(lowerPanel, BorderLayout.PAGE_END);

        pack();
        Util.centerComponent(this);
    }

    /**
     * Size Column
     *
     * @param table
     * @param col
     * @param size
     */
    private void sizeTableColumns(JTable table, int col, int size) {
        final TableColumn column = table.getColumnModel().getColumn(col);
        column.setPreferredWidth(size);
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
        if (e.getSource().equals(addButton)) {
            model.addLot();
            deleteButton.setEnabled(true);
        } else if (e.getSource().equals(okButton)) {
            assetPanel.refresh();
            closeDialog();
        } else {
            if (table.getSelectedRow() >= 0) {
                model.removeLot(table.getSelectedRow());

                if (asset.getLots() != null && asset.getLots().size() > 0) {
                    deleteButton.setEnabled(true);
                } else {
                    deleteButton.setEnabled(false);
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        assetPanel.refresh();
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
