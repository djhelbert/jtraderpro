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
import com.jtraderpro.model.AssetGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Asset Group Panel
 *
 * @author djhelbert
 */
public class AssetGroupPanel extends JPanel {

    public static int ROW_MAX = 8;
    public static int COL_MAX = 5;
    public static int MAX = 40;

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static final Logger logger = LoggerFactory.getLogger(AssetGroupPanel.class);

    private final List<AssetPanel> assetPanels = new ArrayList<>();
    private AssetGroup group;

    /**
     * Constructor w/Empty Group
     */
    public AssetGroupPanel() {
        this(new AssetGroup());
    }

    /**
     * Constructor
     *
     * @param group
     */
    public AssetGroupPanel(AssetGroup group) {
        super();
        this.group = group;
        init();
        update();
        executor.scheduleAtFixedRate(new UpdateInfoTask(), 60, 60, TimeUnit.SECONDS);
    }

    /**
     * Get Asset Group for Panel
     *
     * @return
     */
    public AssetGroup getAssetGroup() {
        return group;
    }

    /**
     * Shutdown Executor
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * Update
     */
    private void update() {
        group.getAssets().forEach((asset) -> {
            assetPanels.get(asset.getOrder()).refresh(asset);
        });
    }

    /**
     * Initialize Panel
     */
    private void init() {
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setLayout(new GridLayout(ROW_MAX, COL_MAX));

        int order = 0;

        for (int i = 0; i < ROW_MAX; i++) {
            for (int j = 0; j < COL_MAX; j++) {
                AssetPanel panel = new AssetPanel(group, order);
                add(panel);
                assetPanels.add(panel);
                order++;
            }
        }
    }

    /**
     * Update Asset Order and Refresh Each
     */
    public void sort() {
        for (int i = 0; i < MAX; i++) {
            assetPanels.get(i).empty();
        }

        group.getAssets().sort(null);

        int index = 0;

        for (Asset ass : group.getAssets()) {
            ass.setOrder(index);
            assetPanels.get(index).refresh(ass);
            index++;
        }
    }

    /**
     * Runnable Update Task
     */
    private class UpdateInfoTask implements Runnable {
        public UpdateInfoTask() {
        }

        @Override
        public void run() {
            try {
                assetPanels.forEach((panel) -> {
                    panel.updateInfo();
                });
            } catch (Exception e) {
                logger.error("Update Info Task", e);
            }
        }
    }

}
