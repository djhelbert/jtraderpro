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
import com.jtraderpro.PortfolioProvider;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Frame
 *
 * @author djhelbert
 */
public class MainFrame extends JFrame implements ActionListener {

    private final static JMenuItem exitItem = new JMenuItem("Exit");
    private final static JMenuItem newItem = new JMenuItem("New");
    private final static JMenuItem saveItem = new JMenuItem("Save");
    private final static JMenuItem aboutItem = new JMenuItem("About...");
    private final static JMenuItem licenseItem = new JMenuItem("License");
    private final static PortfolioPanel portfolioPanel = new PortfolioPanel();
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Constructor
     */
    public MainFrame() {
        super("JTraderPro 1.0.0");
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        aboutItem.setIcon(Util.getImageIcon("about.png"));
        licenseItem.setIcon(Util.getImageIcon("business.png"));

        final JMenu helpMenu = new JMenu("Help");
        helpMenu.add(aboutItem);
        helpMenu.add(licenseItem);

        licenseItem.addActionListener(this);
        aboutItem.addActionListener(this);

        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");

        newItem.setIcon(Util.getImageIcon("file.png"));
        saveItem.setIcon(Util.getImageIcon("save.png"));
        exitItem.setIcon(Util.getImageIcon("exit.png"));

        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        saveItem.addActionListener(this);
        newItem.addActionListener(this);
        exitItem.addActionListener(this);

        setJMenuBar(menuBar);
        setContentPane(portfolioPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitItem)) {
            exitItemAction();
        }
        if (e.getSource().equals(newItem)) {
            newItemAction();
        }
        if (e.getSource().equals(saveItem)) {
            saveItemAction();
        }
        if (e.getSource().equals(licenseItem)) {
            licenseAction();
        }
        if (e.getSource().equals(aboutItem)) {
            aboutAction();
        }
    }

    /**
     * Save Portfolio
     */
    private void saveItemAction() {
        try {
            PortfolioProvider.getInstance().save();
        } catch (IOException err) {
            logger.error("Save Item", err);
        }
    }

    /**
     * Exit
     */
    private void exitItemAction() {
        System.exit(0);
    }

    /**
     * Create New Portfolio & Reload Tabs
     */
    private void newItemAction() {
        PortfolioProvider.getInstance().getNewPortfolio();
        portfolioPanel.load();
    }

    /**
     * About Action
     */
    private void aboutAction() {
        Util.showInfo(getMainComponent(), "JTraderPro 1.0.0 Copyright 2020", "About");
    }

    /**
     * License Action
     */
    private void licenseAction() {
        try {
            final String text = Util.getFileText("license.txt");
            final JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(5, 5));

            final JTextArea textArea = new JTextArea(text, 25, 80);
            textArea.setEditable(false);
            textArea.setFont(new Font("courier", Font.PLAIN, 12));

            panel.setBorder(new EtchedBorder());
            panel.add(BorderLayout.CENTER, new JScrollPane(textArea));

            Util.showInfo(this, panel, "License");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * Get Main Component
     *
     * @return Component
     */
    public static Component getMainComponent() {
        return portfolioPanel;
    }
}
