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
package com.jtraderpro;

import com.jtraderpro.ui.MainFrame;
import com.jtraderpro.ui.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;

/**
 * Main
 *
 * @author djhelbert
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static MainFrame frame;

    /**
     * Main
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception err) {
            logger.error("UI Mananger Error", err);
        }

        frame = new MainFrame();
        frame.setSize(1000, 800);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setIconImage(Util.getImageIcon("stocks.png").getImage());

        Util.centerComponent(frame);
    }

    /**
     * Initialize Portfolio
     */
    private static void init() {
        if (!PortfolioProvider.getInstance().defaultExists()) {
            PortfolioProvider.getInstance().getNewPortfolio();

            try {
                PortfolioProvider.getInstance().save();
            } catch (IOException err) {
                logger.error("Portfolio Provider", err);
            }
        } else {
            try {
                PortfolioProvider.getInstance().load();
            } catch (IOException err) {
                logger.error("Main Error", err);
            }
        }
    }

    public static JFrame getMainFrame() {
        return frame;
    }
}
