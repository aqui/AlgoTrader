package com.algotrader;

import java.util.TimerTask;

import org.json.JSONObject;

public class HorizontalTrader extends TimerTask 
{
	private double newValue;
	private double lastSoldAt;
	private double lastBoughtAt = 0.100d;
	private double totalBTC = 0d;
	private double totalCoin = 2d;
	private double initialCoin = 2d;
	private double initialCostBTC = ((lastBoughtAt*totalCoin)+(lastBoughtAt*totalCoin)*0.25/100);
	private double preTotalBTC;
	private double preTotalCoin;
	private String coin;
	private String spentCoin;
	private double low;
	private double high;
	private Bittrex bittrex;
	private int waiting = 0;
	private JSONObject json;
	private String lastUUID;
	private double baseVolume;
	private double prevDay;
	private String timeStamp;
	private String created;
	private int openSellOrders;
	private double volume;
	private int openBuyOrders;
	private double ask; //lowest priced sell order
	private double bid; //highest priced buy order
	private double last; //the price at which the last trade occurred
	private int boughtSold = 0; //bought=1, sold=2
	private String market;
	
	public HorizontalTrader(Bittrex bittrex, String spentCoin, String coin)
	{
		this.bittrex = bittrex;
		this.spentCoin = spentCoin;
		this.coin = coin;
		this.market = spentCoin+"-"+coin;
		setMarketValues(spentCoin+"-"+coin);
		System.out.println("Initial buy price: "+lastBoughtAt+" "+spentCoin.toUpperCase());
		System.out.println("Initial total "+coin.toUpperCase()+": "+totalCoin);
		System.out.println("Initial cost: "+initialCostBTC+" "+spentCoin.toUpperCase());
		System.out.println("ASK: "+ask);
		System.out.println("BID: "+bid);
		System.out.println("LAST: "+last);
		System.out.println("LOW: "+low);
		System.out.println("HIGH: "+high);
		System.out.println("Getting ready to trade...");
		System.out.println("--------------------------------");
	}
	
	public void setMarketValues(String market)
	{
		json = new JSONObject(bittrex.getMarketSummary(market));
		baseVolume = (double) json.getJSONArray("result").getJSONObject(0).get("BaseVolume");
		prevDay = (double) json.getJSONArray("result").getJSONObject(0).get("PrevDay");
		timeStamp = (String) json.getJSONArray("result").getJSONObject(0).get("TimeStamp");
		created = (String) json.getJSONArray("result").getJSONObject(0).get("Created");
		openSellOrders = (int) json.getJSONArray("result").getJSONObject(0).get("OpenSellOrders");
		volume = (double) json.getJSONArray("result").getJSONObject(0).get("Volume");
		openBuyOrders = (int) json.getJSONArray("result").getJSONObject(0).get("OpenBuyOrders");
		ask = (double) json.getJSONArray("result").getJSONObject(0).get("Ask");
		bid = (double) json.getJSONArray("result").getJSONObject(0).get("Bid");
		last = (double) json.getJSONArray("result").getJSONObject(0).get("Last");
		low = last = (double) json.getJSONArray("result").getJSONObject(0).get("Low");
		high = (double) json.getJSONArray("result").getJSONObject(0).get("High");
	}
	
	public void run() 
	{
		try 
		{
			setMarketValues(spentCoin+"-"+coin);
		} 
		catch (Exception e) 
		{
			return;
		}
		
		if(boughtSold == 0) //sell them all first
		{
			newValue = bid;
			if(newValue > lastBoughtAt)
			{
				preSell();
				//waiting = 1;
			}
		}
		else
		{
			if(boughtSold == 2) //if it's sold
			{
				newValue = ask;
				if(newValue < lastSoldAt && totalBTC > 0 && newValue>=low)
				{
					preBuy();
				}
			}
			else if(boughtSold == 1) //if it's bought
			{
				newValue = bid;
				if(newValue > lastBoughtAt && totalCoin > 0 && newValue<=high)
				{
					preSell();
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
		preTotalCoin = totalCoin;
		double incomeBTC = (newValue * totalCoin) - (((newValue * totalCoin) * 0.25) / 100);
		if(incomeBTC < preTotalBTC)
		{
			return;
		}
		if(incomeBTC < initialCostBTC)
		{
			return;
		}
		String orderBook = bittrex.getOrderBook(market, "buy");
		JSONObject orderjson = new JSONObject(orderBook);
		double orderQuantity = (double) orderjson.getJSONArray("result").getJSONObject(0).get("Quantity");
		if(orderQuantity < totalCoin)
		{
			return;
		}
		String sold = placeSell(String.valueOf(totalCoin), String.valueOf(newValue));
		JSONObject jsons = new JSONObject(sold);
		if(!jsons.get("success").toString().equals("false"))
		{
			lastUUID = (String) jsons.getJSONArray("result").getJSONObject(0).get("uuid");
			System.out.println(lastUUID);
		}
		else
		{
			//return;
		}
		waiting = 1;
		totalBTC = incomeBTC;
		totalCoin = 0;
		lastSoldAt = newValue;
		System.out.println("Sold at: "+newValue+" BTC");
		printEmAll();
		boughtSold = 2;
	}
	
	private void preBuy() 
	{
		preTotalBTC = totalBTC;
		double incomeCoin = (totalBTC / newValue) - (((totalBTC / newValue) * 0.25) / 100);
		if(incomeCoin < preTotalCoin)
		{
			return;
		}
		if(incomeCoin < initialCoin)
		{
			return;
		}
		String orderBook = bittrex.getOrderBook(market, "sell");
		JSONObject orderjson = new JSONObject(orderBook);
		double orderQuantity = (double) orderjson.getJSONArray("result").getJSONObject(0).get("Quantity");
		if(orderQuantity < totalBTC)
		{
			return;
		}
		String bought = placeBuy(String.valueOf(totalBTC), String.valueOf(newValue));
		JSONObject jsons = new JSONObject(bought);
		if(!jsons.get("success").toString().equals("false"))
		{
			lastUUID = (String) jsons.getJSONArray("result").getJSONObject(0).get("uuid");
			System.out.println(lastUUID);
		}
		else
		{
			//return;
		}
		waiting = 0;
		totalCoin = incomeCoin;
		totalBTC = 0;
		lastBoughtAt = newValue;
		System.out.println("Bought at: "+newValue+" BTC");
		printEmAll();
		boughtSold = 1;
	}
	
	private void printEmAll()
	{
		System.out.printf("Total BTC: %.12f\n", totalBTC);
		System.out.printf("Total "+coin.toUpperCase()+": %.12f\n", totalCoin);
		System.out.println("--------------------------------");
	}
	
	private String placeSell(String quantity, String rate)
	{
		return bittrex.sellLimit(market, quantity, rate);
	}
	
	private String placeBuy(String quantity, String rate)
	{
		return bittrex.buyLimit(market, quantity, rate);
	}

	private void cancelBuyOrSell(String uuid)
	{
		
	}
	
	public Bittrex getBittrex() 
	{
		return bittrex;
	}

	public void setBittrex(Bittrex bittrex) 
	{
		this.bittrex = bittrex;
	}

	public String getSpentCoin() {
		return spentCoin;
	}

	public void setSpentCoin(String spentCoin) {
		this.spentCoin = spentCoin;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}
}