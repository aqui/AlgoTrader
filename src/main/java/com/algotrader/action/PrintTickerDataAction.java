package com.algotrader.action;

import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.algotrader.ChartData;
import com.algotrader.ChartDataList;
import com.algotrader.ticker.TickerRecord;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrintTickerDataAction implements Action1<PubSubData>
{
	private String chartDataURI;
	
	public PrintTickerDataAction(String chartDataURI)
	{
		this.chartDataURI = chartDataURI;
	}
	
	@Override
	public void call(PubSubData data)
	{
		try 
		{
			TickerRecord record = new TickerRecord(data);
			if(record.toString()!=null)
			{
				System.out.println(record);
				returnChartData();
			}
		} 
		catch (Exception e) 
		{
			System.err.println(e);
		}
	}
	
	public void returnChartData() throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
        ObjectMapper mapper = new ObjectMapper();
        List<ChartData> results = mapper.readValue(new URL(chartDataURI), new TypeReference<List<ChartData>>(){});
        System.out.println(results.get(0).getHigh());
	}
}