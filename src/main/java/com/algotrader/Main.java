package com.algotrader;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) throws Exception {
		// int daysBefore = 1;
		// long start =
		// ZonedDateTime.now(ZoneOffset.UTC).minusDays(daysBefore).toEpochSecond();
		// long end = ZonedDateTime.now(ZoneOffset.UTC).minusDays(0).toEpochSecond();
		// //300: 5dk, 900: 15dk, 1800: 30dk, 7200: 2sa, 14400: 4sa, 86400: 1gun
		// long period = 300;
		// String currencyPair = "BTC_ETH";
		// String chartDataURI =
		// "https://poloniex.com/public?command=returnChartData&currencyPair="+currencyPair+"&start="+start+"&end="+end+"&period="+period;
		// System.out.println(chartDataURI);
		// PoloniexClient poloniexClient = new PoloniexClient(chartDataURI);
		// poloniexClient.open();
		// while(true)
		// {
		//
		// }
		// Bittrex
		Timer time = new Timer();
		String apikey = "asd";
		String secret = "asd";
		Bittrex bittrex = new Bittrex(apikey, secret, 1, 1);
		//Bittrex, market, currency, initial buy price, initial total coin
		LinearTrader st = new LinearTrader(bittrex, "btc", "eth", 0.05d, 0.08485771d);
		time.schedule(st, 0, 1000);
	}
}
