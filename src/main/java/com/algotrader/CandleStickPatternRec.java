package com.algotrader;

import java.util.Date;
import java.util.List;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;

public class CandleStickPatternRec 
{
	private Date[] dateArray;
	private double[] highArray;
	private double[] lowArray;
	private double[] closeArray;
	private double[] openArray;
	private double[] volumeArray;
	private double[] quoteVolumeArray;
	private double[] weightedAverageArray;
	private Core core = new Core();
	
	public CandleStickPatternRec() 
	{
		
	}

	public int[] recognize(List<Object> candleStickList) 
	{
		dateArray = (Date[]) candleStickList.get(0);
		highArray = (double[]) candleStickList.get(1);
		lowArray = (double[]) candleStickList.get(2);
		closeArray = (double[]) candleStickList.get(3);
		openArray = (double[]) candleStickList.get(4);
		volumeArray = (double[]) candleStickList.get(5);
		quoteVolumeArray = (double[]) candleStickList.get(6);
		weightedAverageArray = (double[]) candleStickList.get(7);
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int[] outInteger = new int[dateArray.length];
		core.cdlDoji(0, dateArray.length-1, openArray, highArray, lowArray, closeArray, outBegIdx, outNBElement, outInteger);
		return outInteger;
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
