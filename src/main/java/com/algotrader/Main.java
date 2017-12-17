package com.algotrader;

import java.util.Timer;

public class Main {
	public static void main(String[] args) throws Exception 
	{
		Timer time = new Timer();
		String market = "BTC";
		String coin = "ETH";
		Bittrex bittrex = new Bittrex(apikey, secret, 1, 1);
		LinearTrader st = new LinearTrader(bittrex, market, coin);
		time.schedule(st, 0, 3000);
	}
}

