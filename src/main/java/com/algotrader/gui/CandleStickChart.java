package com.algotrader.gui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CandleStickChart extends ApplicationFrame 
{
	private static final long serialVersionUID = 1L;
	private Date[] dateArray;
	private double[] highArray;
	private double[] lowArray;
	private double[] closeArray;
	private double[] openArray;
	private double[] volumeArray;
	private double[] quoteVolumeArray;
	private double[] weightedAverageArray;
	
	public CandleStickChart(List<Object> candleStickPattern) 
	{
		super("kendıl sitik");
		dateArray = (Date[]) candleStickPattern.get(0);
		highArray = (double[]) candleStickPattern.get(1);
		lowArray = (double[]) candleStickPattern.get(2);
		closeArray = (double[]) candleStickPattern.get(3);
		openArray = (double[]) candleStickPattern.get(4);
		volumeArray = (double[]) candleStickPattern.get(5);
		quoteVolumeArray = (double[]) candleStickPattern.get(6);
		weightedAverageArray = (double[]) candleStickPattern.get(7);
		final DefaultHighLowDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
		setContentPane(chartPanel);
		setVisible(true);
	}

	private DefaultHighLowDataset createDataset() 
	{
		DefaultHighLowDataset data = new DefaultHighLowDataset("", dateArray, highArray, lowArray, closeArray, openArray, volumeArray);
		return data;
	}

	private JFreeChart createChart(final DefaultHighLowDataset dataset) {
		final JFreeChart chart = ChartFactory.createCandlestickChart("Candlestick Demo", "Time", "Price", dataset,
				false);
		return chart;
	}
}
