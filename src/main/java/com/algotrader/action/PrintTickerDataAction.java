package com.algotrader.action;

import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.algotrader.CandleStickPatternRec;
import com.algotrader.ChartData;
import com.algotrader.Indicator;
import com.algotrader.ticker.TickerRecord;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrintTickerDataAction implements Action1<PubSubData>
{
	private String chartDataURI;
	private Date[] dateArray;
	private double[] highArray;
	private double[] lowArray;
	private double[] closeArray;
	private double[] openArray;
	private double[] volumeArray;
	private double[] quoteVolumeArray;
	private double[] weightedAverageArray;
	private CandleStickPatternRec candleStickPatternRec = new CandleStickPatternRec();
	private Indicator indicator = new Indicator();
	private List<Object> candleStickPattern = new ArrayList<Object>();
	
	public CandleStickPatternRec getCandleStickPatternRec() {
		return candleStickPatternRec;
	}

	public void setCandleStickPatternRec(CandleStickPatternRec candleStickPatternRec) {
		this.candleStickPatternRec = candleStickPatternRec;
	}

	public List<Object> getCandleStickPattern() {
		return candleStickPattern;
	}

	public void setCandleStickPattern(List<Object> candleStickPattern) {
		this.candleStickPattern = candleStickPattern;
	}

	public PrintTickerDataAction(String chartDataURI)
	{
		this.chartDataURI = chartDataURI;
	}
	
	@Override
	public void call(PubSubData data)
	{
		try 
		{
			TickerRecord record = new TickerRecord(data);
			if(record.toString()!=null)
			{
				System.out.println(record);
				returnChartData();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void returnChartData() throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
        ObjectMapper mapper = new ObjectMapper();
        List<ChartData> chartDataList = mapper.readValue(new URL(chartDataURI), new TypeReference<List<ChartData>>(){});
        parseChartData(chartDataList);
        candleStickPattern.add(dateArray);
        candleStickPattern.add(highArray);
        candleStickPattern.add(lowArray);
        candleStickPattern.add(closeArray);
        candleStickPattern.add(openArray);
        candleStickPattern.add(volumeArray);
        candleStickPattern.add(quoteVolumeArray);
        candleStickPattern.add(weightedAverageArray);
        candleStickPatternRec.recognize(candleStickPattern);
        indicator.setIndicators(candleStickPattern);
        candleStickPattern.clear();
	}
	
	public void parseChartData(List<ChartData> chartDataList)
	{
		int chartDataListSize = chartDataList.size();
		dateArray = new Date[chartDataListSize];
		highArray = new double[chartDataListSize];
		lowArray = new double[chartDataListSize];
		closeArray = new double[chartDataListSize];
		openArray = new double[chartDataListSize];
		volumeArray = new double[chartDataListSize];
		quoteVolumeArray = new double[chartDataListSize];
		weightedAverageArray = new double[chartDataListSize];
		
		for (ChartData chartData : chartDataList) 
		{
			dateArray[chartDataList.indexOf(chartData)] = chartData.getDate();
			highArray[chartDataList.indexOf(chartData)] = chartData.getHigh();
			lowArray[chartDataList.indexOf(chartData)] = chartData.getLow();
			closeArray[chartDataList.indexOf(chartData)] = chartData.getClose();
			openArray[chartDataList.indexOf(chartData)] = chartData.getOpen();
			volumeArray[chartDataList.indexOf(chartData)] = chartData.getVolume();
			quoteVolumeArray[chartDataList.indexOf(chartData)] = chartData.getQuoteVolume();
			weightedAverageArray[chartDataList.indexOf(chartData)] = chartData.getWeightedAverage();
		}
	}

	public String getChartDataURI() {
		return chartDataURI;
	}

	public void setChartDataURI(String chartDataURI) {
		this.chartDataURI = chartDataURI;
	}

	public Date[] getDateArray() {
		return dateArray;
	}

	public void setDateArray(Date[] dateArray) {
		this.dateArray = dateArray;
	}

	public double[] getHighArray() {
		return highArray;
	}

	public void setHighArray(double[] highArray) {
		this.highArray = highArray;
	}

	public double[] getLowArray() {
		return lowArray;
	}

	public void setLowArray(double[] lowArray) {
		this.lowArray = lowArray;
	}

	public double[] getCloseArray() {
		return closeArray;
	}

	public void setCloseArray(double[] closeArray) {
		this.closeArray = closeArray;
	}

	public double[] getOpenArray() {
		return openArray;
	}

	public void setOpenArray(double[] openArray) {
		this.openArray = openArray;
	}

	public double[] getVolumeArray() {
		return volumeArray;
	}

	public void setVolumeArray(double[] volumeArray) {
		this.volumeArray = volumeArray;
	}

	public double[] getQuoteVolumeArray() {
		return quoteVolumeArray;
	}

	public void setQuoteVolumeArray(double[] quoteVolumeArray) {
		this.quoteVolumeArray = quoteVolumeArray;
	}

	public double[] getWeightedAverageArray() {
		return weightedAverageArray;
	}

	public void setWeightedAverageArray(double[] weightedAverageArray) {
		this.weightedAverageArray = weightedAverageArray;
	}
}