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
import com.jtraderpro.model.Alert;
import com.jtraderpro.model.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Alert Panel
 */
public class AlertDialog extends JDialog implements ActionListener  {
    private JPanel updatePanel = new JPanel();
    private JTextField priceField = new JTextField("0.00");
    private JLabel symbolField = new JLabel();
    private JComboBox<String> type = new JComboBox(new Object[]{"ABOVE", "BELOW"});
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("CANCEL");
    private JPanel buttonPanel = new JPanel();
    private Asset asset;

    /**
     * Constructor
     *
     * @param asset
     */
    public AlertDialog(Asset asset) {
        super(Main.getMainFrame());

        this.asset = asset;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Update Alert");

        updatePanel.setLayout(new GridLayout(3, 2, 10, 10));
        updatePanel.add(new JLabel("Symbol"));
        updatePanel.add(symbolField);
        updatePanel.add(new JLabel("Alert Type"));
        updatePanel.add(type);
        updatePanel.add(new JLabel("Price Threshold"));
        updatePanel.add(priceField);

        symbolField.setText(asset.getSymbol());

        if(asset.getAlert() != null) {
            priceField.setText(asset.getAlert().getPrice().toString());
            if (asset.getAlert().getAbove()) {
                type.setSelectedItem("ABOVE");
            } else {
                type.setSelectedItem("BELOW");
            }
        } else {
            priceField.setText("0.00");
        }

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        okButton.setIcon(Util.getImageIcon("check.png"));
        cancelButton.setIcon(Util.getImageIcon("delete.png"));

        final Container cont = getContentPane();
        cont.setSize(new Dimension(350, 80));
        cont.setLayout(new BorderLayout());
        cont.add(updatePanel,BorderLayout.CENTER);
        cont.add(buttonPanel,BorderLayout.SOUTH);

        pack();
        Util.centerComponent(this);
    }

    public Double getPrice() throws NumberFormatException {
        return Double.parseDouble(priceField.getText());
    }

    public boolean isAbove() {
        return "ABOVE".equals(type.getSelectedItem());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == okButton ) {
            try {
                asset.setAlert(new Alert(isAbove(),getPrice()));
                JOptionPane.showMessageDialog(MainFrame.getMainComponent(),"New alert has been added.","Information",JOptionPane.INFORMATION_MESSAGE);
                // Close
                setVisible(false);
                dispose();
            } catch(NumberFormatException err) {
                JOptionPane.showMessageDialog(MainFrame.getMainComponent(),"Not a valid number.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Close
            setVisible(false);
            dispose();
        }
    }
}
