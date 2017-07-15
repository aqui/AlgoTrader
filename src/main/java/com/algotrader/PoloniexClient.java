package com.algotrader;

import java.util.concurrent.TimeUnit;

import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;
import com.algotrader.action.ClientStatusChangedAction;
import com.algotrader.orderbook.OrderBook;

public class PoloniexClient
{
	private static final String POLONIEX_API_URL = "wss://api.poloniex.com";
	private static final String REALM = "realm1";
	private static final int RECONNECT_INTERVAL_SEC = 5;

	private final WampClient client;

	private final OrderBook orderBookAsk;
	private final OrderBook orderBookBid;

	public PoloniexClient(String chartDataURI) throws Exception
	{
		NettyWampClientConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();
		WampClientBuilder builder = new WampClientBuilder();
		builder.withConnectorProvider(connectorProvider);
		builder.withUri(POLONIEX_API_URL);
		builder.withRealm(REALM);
		builder.withInfiniteReconnects();
		builder.withReconnectInterval(RECONNECT_INTERVAL_SEC, TimeUnit.SECONDS);
		client = builder.build();
		orderBookAsk = new OrderBook();
		orderBookBid = new OrderBook();
		client.statusChanged().subscribe(new ClientStatusChangedAction(client, orderBookAsk, orderBookBid, chartDataURI));
	}

	public void open()
	{
		client.open();
	}

	public void close()
	{
		client.close().toBlocking().last();
	}

}
