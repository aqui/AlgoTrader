package com.algotrader;

import java.util.TimerTask;

import org.json.JSONObject;

public class ScheduledTask extends TimerTask 
{
	private double newValue;
	private double lastSoldAt;
	private double lastBoughtAt;
	private double initialBoughtAt = 0.10d;
	private double totalBTC = 0d;
	private double totalCoin = 3d;
	private String coin = "bcc";
	private double low;
	private double high;
	private Bittrex bittrex;
	private int waiting = 0;
	private JSONObject json;
	
	public ScheduledTask()
	{
		System.out.println("Initial buy price: "+initialBoughtAt+" BTC");
		System.out.println("Initial total "+coin.toUpperCase()+": "+totalCoin);
		System.out.println("Initial cost: "+((initialBoughtAt*totalCoin)+(initialBoughtAt*totalCoin)*0.25/100));
		System.out.println("Getting ready to trade...");
		System.out.println("--------------------------------");
	}
	
	public void run() 
	{
		try 
		{
			json = new JSONObject(bittrex.getMarketSummary("btc-"+coin));
		} 
		catch (Exception e) 
		{
			return;
		}
		double current = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
		high = (double) json.getJSONArray("result").getJSONObject(0).get("High");
		low = (double) json.getJSONArray("result").getJSONObject(0).get("Low");
		if(newValue == 0)
		{
			newValue = current;
			if(newValue > initialBoughtAt)
			{
				preSell();
				waiting = 1;
			}
		}
		else
		{
			if(newValue != current)
			{
				newValue = current;
				if(newValue > lastBoughtAt && totalCoin > 0 && newValue<=high)
				{
					preSell();
				}
				if(newValue < lastSoldAt && totalBTC > 0 && newValue>=low)
				{
					preBuy();
				}
			}
		}
		if(waiting==0)
		{
			System.out.println("waiting to sell...");
			System.out.println("--------------------------------");
			waiting = 2;
		}
		if(waiting==1)
		{
			System.out.println("waiting to buy...");
			System.out.println("--------------------------------");
			waiting = 2;
		}
	}

	private void preSell() 
	{
		double incomeBTC = (newValue * totalCoin) - (((newValue * totalCoin) * 0.25) / 100);
		if(totalBTC!=0)
		{
			if(totalBTC<(totalBTC+incomeBTC))
			{
				return;
			}
		}
		waiting = 1;
		totalBTC += incomeBTC;
		totalCoin = 0;
		lastSoldAt = newValue;
		System.out.println("Sold at: "+newValue+" BTC");
		printEmAll();
	}
	
	private void preBuy() 
	{
		double incomeCoin = (totalBTC / newValue) - (((totalBTC / newValue) * 0.25) / 100);
		if(totalCoin<(totalCoin+incomeCoin))
		{
			return;
		}
		waiting = 0;
		totalCoin += incomeCoin;
		totalBTC = 0;
		lastBoughtAt = newValue;
		System.out.println("Bought at: "+newValue+" BTC");
		printEmAll();
	}
	
	private void printEmAll()
	{
		System.out.printf("New Price: %.12f BTC\n", newValue);
		System.out.printf("Total BTC: %.12f\n", totalBTC);
		System.out.printf("Total "+coin.toUpperCase()+": %.12f\n", totalCoin);
		System.out.println("--------------------------------");
	}
	
	private void realSell()
	{
		
	}
	
	private void realBuy()
	{
		
	}

	public Bittrex getBittrex() {
		return bittrex;
	}

	public void setBittrex(Bittrex bittrex) {
		this.bittrex = bittrex;
	}
}