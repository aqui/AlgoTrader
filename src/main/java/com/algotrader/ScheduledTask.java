package com.algotrader;

import java.util.TimerTask;

import org.json.JSONObject;

public class ScheduledTask extends TimerTask 
{
	public static double lastValue;
	public static double newValue;
	public static double boughtAt = 0.01049500d;
	public static double totalBTC = 0.0000000d;
	public static double totalWAVES = 3d;
	public static double preWAVES;
	public static double preBTC;
	
	public void run() 
	{
		String apikey = "asd";
		String secret = "asd";
		Bittrex bittrex = new Bittrex(apikey, secret, 1, 1);
		JSONObject json = new JSONObject(bittrex.getMarketSummary("btc-neo"));
		double current = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
		if(newValue==0)
		{
			newValue = current;
			lastValue = current;
			if(newValue > boughtAt)
			{
				sell();
			}
		}
		else
		{
			if(newValue != (double) json.getJSONArray("result").getJSONObject(0).get("Last"))
			{
				lastValue = newValue;
				newValue = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
				if(newValue > lastValue && totalWAVES > 0)
					buy();
				if(newValue < lastValue && totalBTC > 0)
					sell();
			}
		}
	}

	private void sell() 
	{
		preWAVES = totalWAVES;
		double satis = newValue * totalWAVES;
		satis = satis - (satis * 0.25) / 100;
		if(totalBTC < satis)
		{
			totalBTC += satis;
			totalWAVES = 0;
		}
		else
		{
			return;
		}
		System.out.println("sold");
		printEmAll();
	}
	
	private void buy() 
	{
		preBTC = totalBTC;
		double alis = totalBTC / newValue;
		alis = alis - (alis * 0.25) / 100;
		if(preWAVES < alis)
		{
			totalWAVES += alis;
			totalBTC = 0;
		}
		else
		{
			return;
		}
		System.out.println("bought");
		printEmAll();
	}
	
	private void printEmAll()
	{
		System.out.printf("new: %.8f\n", newValue);
		System.out.printf("last: %.8f\n", lastValue);
		System.out.printf("BTC: %.8f\n", totalBTC);
		System.out.printf("NEO: %.8f\n", totalWAVES);
		System.out.println("--------------------------------");
	}
}