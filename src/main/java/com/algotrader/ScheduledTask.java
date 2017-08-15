package com.algotrader;

import java.util.TimerTask;

import org.json.JSONObject;

public class ScheduledTask extends TimerTask 
{
	public static double lastValue;
	public static double newValue;
	public static double boughtAt = 0.00111879d;
	public static double totalBTC = 0.00000003d;
	public static double totalWAVES = 10d;
	public static double limitWAVES = 10d;
	
	public void run() 
	{
		String apikey = "asd";
		String secret = "asd";
		Bittrex bittrex = new Bittrex(apikey, secret, 1, 1);
		JSONObject json = new JSONObject(bittrex.getMarketSummary("btc-waves"));
		if(newValue==0)
		{
			newValue = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
			lastValue = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
			if(newValue > boughtAt)
				sell();
		}
		else
		{
			if(newValue != (double) json.getJSONArray("result").getJSONObject(0).get("Last"))
			{
				lastValue = newValue;
				newValue = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
				if(newValue > lastValue && totalWAVES > 0)
					sell();
				if(newValue < lastValue && totalBTC > 0)
					buy();
			}
		}
	}

	private void sell() 
	{
		System.out.println("sold");
		double satis = newValue * totalWAVES;
		satis = satis - (satis * 0.25) / 100;
		totalBTC += satis;
		totalWAVES = 0;
		printEmAll();
	}
	
	private void buy() 
	{
		System.out.println("bought");
		double alis = totalBTC / newValue;
		alis = alis - (alis * 0.25) / 100;
		totalWAVES += alis;
		totalBTC = 0;
		printEmAll();
	}
	
	private void printEmAll()
	{
		System.out.printf("new: %.8f\n", newValue);
		System.out.printf("last: %.8f\n", lastValue);
		System.out.printf("BTC: %.8f\n", totalBTC);
		System.out.printf("WAVES: %.8f\n", totalWAVES);
		System.out.println("--------------------------------");
	}
}