package com.algotrader;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		int daysBefore = 1;
		long start = ZonedDateTime.now(ZoneOffset.UTC).minusDays(daysBefore).toEpochSecond();
		long end = ZonedDateTime.now(ZoneOffset.UTC).minusDays(0).toEpochSecond();
		//300: 5dk, 900: 15dk, 1800: 30dk, 7200: 2sa, 14400: 4sa, 86400: 1gun
		long period = 300;
		String currencyPair = "BTC_ETH";
		String chartDataURI = "https://poloniex.com/public?command=returnChartData&currencyPair="+currencyPair+"&start="+start+"&end="+end+"&period="+period;
		System.out.println(chartDataURI);
		PoloniexClient poloniexClient = new PoloniexClient(chartDataURI);
		poloniexClient.open();
		while(true)
		{
			
		}
	}
}
