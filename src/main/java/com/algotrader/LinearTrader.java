package com.algotrader;

import java.util.Date;
import java.util.TimerTask;

import org.json.JSONObject;

public class LinearTrader extends TimerTask 
{
	private double newValue;
	private double lastSoldAt;
	private double lastBoughtAt;
	private double totalBTC = 0d;
	private double totalCoin;
	private double initialCoin;
	private double initialCostBTC;
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
	
	public LinearTrader(Bittrex bittrex, String spentCoin, String coin, double lastBoughtAt, double totalCoin)
	{
		this.bittrex = bittrex;
		this.spentCoin = spentCoin;
		this.coin = coin;
		this.market = spentCoin+"-"+coin;
		this.lastBoughtAt = lastBoughtAt;
		this.totalCoin = totalCoin;
		this.initialCoin = totalCoin;
		this.initialCostBTC = ((lastBoughtAt*totalCoin)+(lastBoughtAt*totalCoin)*0.25/100);
		setMarketValues(spentCoin+"-"+coin);
		System.out.println("---Initial Values---");
		System.out.printf("Initial buy price %.12f "+spentCoin.toUpperCase()+"\n", lastBoughtAt);
		System.out.printf("Initial total %.12f "+coin.toUpperCase()+"\n",totalCoin);
		System.out.printf("Initial cost %.12f "+spentCoin.toUpperCase()+"\n",initialCostBTC);
		System.out.println("---Current Values---");
		System.out.printf("ASK: %.12f\n",ask);
		System.out.printf("BID: %.12f\n",bid);
		System.out.printf("LAST: %.12f\n",last);
		System.out.printf("LOW: %.12f\n",low);
		System.out.printf("HIGH: %.12f\n",high);
		System.out.println("Time: "+new Date());
		System.out.println("---Ready, set, go!---");
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
			if(boughtSold == 0)
			{
				System.out.printf("Bid must be greater than: %.12f \n", (initialCostBTC/(0.9975d*initialCoin)));
			}
			System.out.println("Waiting to sell...");
			System.out.println("--------------------------------");
			waiting = 2;
		}
		if(waiting==1)
		{
			System.out.println("Waiting to buy...");
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
		try 
		{
			String orderBook = bittrex.getOrderBook(market, "buy");
			JSONObject orderjson = new JSONObject(orderBook);
			double orderQuantity = (double) orderjson.getJSONArray("result").getJSONObject(0).get("Quantity");
			if(orderQuantity < totalCoin)
			{
				return;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return;
		}
		String sold = null;
		try 
		{
			sold = bittrex.sellLimit(market, String.valueOf(totalCoin), String.valueOf(newValue));
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return;
		}
		if(sold!=null)
		{
			try 
			{
				JSONObject jsons = new JSONObject(sold);
				if(!jsons.get("success").toString().equals("false"))
				{
					lastUUID = (String) jsons.getJSONArray("result").getJSONObject(0).get("uuid");
					System.out.println(lastUUID);
				}
				else
				{
					return;
				}
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
		waiting = 1;
		totalBTC = incomeBTC;
		totalCoin = 0;
		lastSoldAt = newValue;
		System.out.printf("Sold at %.12f "+spentCoin.toUpperCase() +" " + new Date()+"\n",newValue);
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
		
		try 
		{
			String orderBook = bittrex.getOrderBook(market, "sell");
			JSONObject orderjson = new JSONObject(orderBook);
			double orderQuantity = (double) orderjson.getJSONArray("result").getJSONObject(0).get("Quantity");
			if(orderQuantity < totalBTC)
			{
				return;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return;
		}
		String bought = null;
		try 
		{
			bought = bittrex.buyLimit(market, String.valueOf(totalBTC), String.valueOf(newValue));
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return;
		}
		if(bought!=null)
		{
			try 
			{
				JSONObject jsons = new JSONObject(bought);
				if(!jsons.get("success").toString().equals("false"))
				{
					lastUUID = (String) jsons.getJSONArray("result").getJSONObject(0).get("uuid");
					System.out.println(lastUUID);
				}
				else
				{
					return;
				}
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
		waiting = 0;
		totalCoin = incomeCoin;
		totalBTC = 0;
		lastBoughtAt = newValue;
		System.out.printf("Bought at %.12f "+spentCoin.toUpperCase()+" "+new Date()+"\n",newValue);
		printEmAll();
		boughtSold = 1;
	}
	
	private void printEmAll()
	{
		System.out.printf("Total BTC: %.12f\n", totalBTC);
		System.out.printf("Total "+coin.toUpperCase()+": %.12f\n", totalCoin);
		System.out.println("Time: "+new Date());
		System.out.println("--------------------------------");
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