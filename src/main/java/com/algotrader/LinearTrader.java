package com.algotrader;

import java.util.TimerTask;

import org.json.JSONObject;

public class LinearTrader extends TimerTask 
{
	private Bittrex bittrex;
	private String market;
	private String coin;
	private JSONObject marketSummary;
	private double baseVolume;
	private double prevDay;
	private String timeStamp;
	private String created;
	private int openSellOrders;
	private double volume;
	private int openBuyOrders;
	private double ask;
	private double bid;
	private double last;
	private double low;
	private double high;
	private LastOrder lastOrder;
	
	public LinearTrader(Bittrex bittrex, String market, String coin)
	{
		this.bittrex = bittrex;
		this.market = market;
		this.coin = coin;
		lastOrder = new LastOrder();
		setLastOrder(lastOrder);
	}

	public void run() 
	{
		setMarketValues(market+"-"+coin);
	}
	
	private void setLastOrder(LastOrder lastOrder)
	{
		lastOrder.setTimeStamp(new JSONObject(bittrex.getOrderHistory(market+"-"+coin)).getJSONArray("result").getJSONObject(0).getString("TimeStamp"));
		lastOrder.setCommission(commission);
		lastOrder.setCondition(condition);
		lastOrder.setConditional(isConditional);
		lastOrder.setPrice(price);
	}

	private void setMarketValues(String marketCoin) 
	{
		marketSummary = new JSONObject(bittrex.getMarketSummary(marketCoin));
		baseVolume = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("BaseVolume");
		prevDay = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("PrevDay");
		timeStamp = (String) marketSummary.getJSONArray("result").getJSONObject(0).get("TimeStamp");
		created = (String) marketSummary.getJSONArray("result").getJSONObject(0).get("Created");
		openSellOrders = (int) marketSummary.getJSONArray("result").getJSONObject(0).get("OpenSellOrders");
		volume = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("Volume");
		openBuyOrders = (int) marketSummary.getJSONArray("result").getJSONObject(0).get("OpenBuyOrders");
		ask = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("Ask");
		bid = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("Bid");
		last = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("Last");
		low = last = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("Low");
		high = (double) marketSummary.getJSONArray("result").getJSONObject(0).get("High");
	}
	
	public Bittrex getBittrex() 
	{
		return bittrex;
	}

	public void setBittrex(Bittrex bittrex) 
	{
		this.bittrex = bittrex;
	}

	@Override
	public String toString() 
	{
		return "LinearTrader [volume=" + volume + ", ask=" + ask + ", bid="
				+ bid + ", last=" + last + ", low=" + low + ", high=" + high + "]";
	}
}