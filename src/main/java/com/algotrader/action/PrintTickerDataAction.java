package com.algotrader.action;

import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;
import com.algotrader.ticker.TickerRecord;

public class PrintTickerDataAction implements Action1<PubSubData>
{
	@Override
	public void call(PubSubData data)
	{
		try 
		{
			TickerRecord record = new TickerRecord(data);
			if(record.toString()!=null)
			{
				System.out.println(record);
			}
		} 
		catch (Exception e) 
		{
			System.err.println(e);
		}
	}
}