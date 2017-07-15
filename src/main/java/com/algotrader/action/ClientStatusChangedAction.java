package com.algotrader.action;

import rx.functions.Action1;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClient.ConnectedState;
import ws.wamp.jawampa.WampClient.ConnectingState;
import ws.wamp.jawampa.WampClient.DisconnectedState;
import ws.wamp.jawampa.WampClient.State;
import com.algotrader.currency.CurrencyPair;
import com.algotrader.orderbook.OrderBook;
import com.algotrader.orderbook.OrderType;

public class ClientStatusChangedAction implements Action1<State>
{
	private final WampClient client;
	private final OrderBook orderBookAsk;
	private final OrderBook orderBookBid;
	private String chartDataURI;
	
	public ClientStatusChangedAction(WampClient client, OrderBook orderBookAsk, OrderBook orderBookBid, String chartDataURI)
	{
		this.client = client;
		this.orderBookAsk = orderBookAsk;
		this.orderBookBid = orderBookBid;
		this.chartDataURI = chartDataURI;
	}

	@Override
	public void call(State status)
	{
		System.err.println("Status: " + status);

		if (status instanceof ConnectedState) 
		{
			subscribeForUpdates();

		} 
		else if (status instanceof ConnectingState)
		{

		} 
		else if (status instanceof DisconnectedState) 
		{

		}
		else 
		{
			System.err.println("Invalid client state!");
		}
	}

	private void subscribeForUpdates()
	{
		client.makeSubscription("ticker").subscribe(new PrintTickerDataAction(chartDataURI));
		//client.makeSubscription(CurrencyPair.BTC_ETH.getCode()).subscribe(new OrderBookUpdateAction(orderBookAsk, OrderType.ASK));
		//client.makeSubscription(CurrencyPair.BTC_ETH.getCode()).subscribe(new OrderBookUpdateAction(orderBookBid, OrderType.BID));
		//client.makeSubscription(CurrencyPair.BTC_ETH.getCode()).subscribe(new TradeHistoryUpdateAction());
	}
}