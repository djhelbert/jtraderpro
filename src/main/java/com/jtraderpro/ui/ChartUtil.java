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

import com.jtraderpro.service.AssetQuote;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 * Chart Utility
 */
public class ChartUtil {

  private static final DecimalFormat decimalformat = new DecimalFormat("#.##");

  private ChartUtil() {
  }

  private static IntervalXYDataset createVolumeDataset(final List<AssetQuote> assetQuotes) {
    final TimeSeries timeseries = new TimeSeries("Volume");

    for (AssetQuote aq : assetQuotes) {
      timeseries.add(new Day(aq.getDate()), aq.getVolume() / 1000000);
    }

    return new TimeSeriesCollection(timeseries);
  }

  private static XYDataset createPriceDataset(final List<AssetQuote> assetQuotes) {
    final TimeSeries timeseries = new TimeSeries("Price");

    for (AssetQuote aq : assetQuotes) {
      timeseries.add(new Day(aq.getDate()), aq.getClose());
    }

    return new TimeSeriesCollection(timeseries);
  }

  public static ChartPanel createChart(final List<AssetQuote> assetQuotes) {
    return createChart(createVolumeDataset(assetQuotes), createPriceDataset(assetQuotes));
  }

  private static ChartPanel createChart(final IntervalXYDataset volumeDataset,
      final XYDataset priceDataSet) {
    JFreeChart jfreechart = ChartFactory
        .createTimeSeriesChart("Price/Volume", "Date", "Price", priceDataSet, true, true, false);

    final XYPlot xyplot = (XYPlot) jfreechart.getPlot();
    NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
    numberaxis.setLowerMargin(0.40000000000000002D);

    numberaxis.setNumberFormatOverride(decimalformat);

    final XYItemRenderer xyitemrenderer = xyplot.getRenderer();

    xyitemrenderer.setDefaultToolTipGenerator(
        new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d M yy"),
            new DecimalFormat("0.00")));

    final NumberAxis numberaxis1 = new NumberAxis("Volume");
    numberaxis1.setUpperMargin(1.0D);

    xyplot.setRangeAxis(1, numberaxis1);
    xyplot.setDataset(1, volumeDataset);
    xyplot.setRangeAxis(1, numberaxis1);
    xyplot.mapDatasetToRangeAxis(1, 1);

    final XYBarRenderer xybarrenderer = new XYBarRenderer(0.20000000000000001D);

    xybarrenderer.setDefaultToolTipGenerator(
        new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d M yy"),
            new DecimalFormat("0,000.00")));
    xyplot.setRenderer(1, xybarrenderer);

    return new ChartPanel(jfreechart, true, true, true, false, true);
  }

}
