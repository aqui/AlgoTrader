package com.algotrader;

import java.util.Date;
import java.util.List;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;

public class Indicator 
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
	double[] outRealUpperBand;
	double[] outRealMiddleBand;
	double[] outRealLowerBand;
	double[] rsiArray;
	double[] sarArray;
	double[] emaArray;
	
	public Indicator()
	{
		
	}
	
	public void setIndicators(List<Object> candleStickList)
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
		outRealUpperBand = new double[closeArray.length];
		outRealMiddleBand = new double[closeArray.length];
		outRealLowerBand = new double[closeArray.length];
		rsiArray = new double[closeArray.length];
		sarArray = new double[closeArray.length];
		emaArray = new double[closeArray.length];
		/*
		 * TA_BBANDS - Bollinger Bands
		 * 
		 * Input  = double
		 * Output = double, double, double
		 * 
		 * Optional Parameters
		 * -------------------
		 * optInTimePeriod:(From 2 to 100000)
		 *    Number of period
		 * 
		 * optInNbDevUp:(From TA_REAL_MIN to TA_REAL_MAX)
		 *    Deviation multiplier for upper band
		 * 
		 * optInNbDevDn:(From TA_REAL_MIN to TA_REAL_MAX)
		 *    Deviation multiplier for lower band
		 * 
		 * optInMAType:
		 *    Type of Moving Average
		 * 
		 * 
		 */
		core.bbands(0, closeArray.length-1, closeArray, 50, 2, 2, MAType.Sma, outBegIdx, outNBElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);
		core.rsi(0, closeArray.length-1, closeArray, 20, outBegIdx, outNBElement, rsiArray);
		core.sar(0, closeArray.length-1, highArray, lowArray, 0.01, 0.02, outBegIdx, outNBElement, sarArray);
		//core.macd(startIdx, endIdx, inReal, optInFastPeriod, optInSlowPeriod, optInSignalPeriod, outBegIdx, outNBElement, outMACD, outMACDSignal, outMACDHist);
		core.ema(0, closeArray.length-1, closeArray, 30, outBegIdx, outNBElement, emaArray);
	}

	public double[] getCloseArray() {
		return closeArray;
	}

	public void setCloseArray(double[] closeArray) {
		this.closeArray = closeArray;
	}

	public Core getCore() {
		return core;
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public double[] getOutRealUpperBand() {
		return outRealUpperBand;
	}

	public void setOutRealUpperBand(double[] outRealUpperBand) {
		this.outRealUpperBand = outRealUpperBand;
	}

	public double[] getOutRealMiddleBand() {
		return outRealMiddleBand;
	}

	public void setOutRealMiddleBand(double[] outRealMiddleBand) {
		this.outRealMiddleBand = outRealMiddleBand;
	}

	public double[] getOutRealLowerBand() {
		return outRealLowerBand;
	}

	public void setOutRealLowerBand(double[] outRealLowerBand) {
		this.outRealLowerBand = outRealLowerBand;
	}

	public double[] getRsiArray() {
		return rsiArray;
	}

	public void setRsiArray(double[] rsiArray) {
		this.rsiArray = rsiArray;
	}
}
