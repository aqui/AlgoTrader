package com.algotrader;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, -30);
		Date oneMonth = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -14);
		Date twoWeeks = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -7);
		Date oneWeek = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -4);
		Date fourDays = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		Date twoDays = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		Date oneDay = calendar.getTime();
		String currencyPair = "BTC_ETH";
		long start = oneMonth.getTime();
		long end = now.getTime();
		long period = 14400;
		String chartDataURI = "https://poloniex.com/public?command=returnChartData&currencyPair="+currencyPair+"&start="+start+"&end="+end+"&period="+period;
		PoloniexClient poloniexClient = new PoloniexClient(chartDataURI);
		poloniexClient.open();
		while(true)
		{
			
		}
	}
}
