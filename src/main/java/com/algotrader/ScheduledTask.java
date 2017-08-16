package com.algotrader;

import java.util.TimerTask;

import org.json.JSONObject;

public class ScheduledTask extends TimerTask 
{
	public static double lastValue;
	public static double newValue;
	public static double boughtAt = 0.1d;
	public static double totalBTC = 0.0000000d;
	public static double totalWAVES = 10;
	public static double preWAVES;
	public static double preBTC;
	public static int i = 0;
	
	public void run() 
	{
		String apikey = "asd";
		String secret = "asd";
//		Bittrex bittrex = new Bittrex(apikey, secret, 1, 1);
//		JSONObject json = new JSONObject(bittrex.getMarketSummary("btc-neo"));
		//double current = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
		double current;
		if(i%2==0)
			current = 0.2d;
		else
			current = 0.1d;
		i++;
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
			if(newValue != current)
			{
				lastValue = newValue;
				newValue = current;
				if(newValue > lastValue && totalWAVES > 0)
					sell();
				if(newValue < lastValue && totalBTC > 0)
					buy();
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
			System.out.println("not sold: "+(totalBTC-satis));
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
			System.out.println("not bought: "+(alis-preWAVES));
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