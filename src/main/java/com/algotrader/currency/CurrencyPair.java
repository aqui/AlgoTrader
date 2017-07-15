package com.algotrader.currency;

public enum CurrencyPair {

	BTC_ETH("BTC_ETH", Currency.BTC, Currency.ETH), 
	BTC_XEM("BTC_XEM", Currency.BTC, Currency.XEM),
	BTC_XMR("BTC_XMR", Currency.BTC, Currency.XMR),
	BTC_XRP("BTC_XRP", Currency.BTC, Currency.XRP),
	BTC_AMP("BTC_AMP", Currency.BTC, Currency.AMP),
	BTC_BTM("BTC_BTM", Currency.BTC, Currency.LSK),
	BTC_ZEC("BTC_ZEC", Currency.BTC, Currency.ZEC),
	BTC_PASC("BTC_PASC", Currency.BTC, Currency.PASC),
	BTC_LSK("BTC_LSK", Currency.BTC, Currency.LSK),
	BTC_XVC("BTC_XVC", Currency.BTC, Currency.XVC),
	BTC_XPM("BTC_XPM", Currency.BTC, Currency.XPM),
	BTC_ARDR("BTC_ARDR", Currency.BTC, Currency.ARDR),
	BTC_BCY("BTC_BCY", Currency.BTC, Currency.BCY),
	BTC_RADS("BTC_RADS", Currency.BTC, Currency.RADS),
	BTC_DOGE("BTC_DOGE", Currency.BTC, Currency.DOGE),
	BTC_FLO("BTC_FLO", Currency.BTC, Currency.FLO),
	BTC_LTC("BTC_LTC", Currency.BTC, Currency.LTC),
	BTC_SJCX("BTC_SJCX", Currency.BTC, Currency.SJCX),
	BTC_FCT("BTC_FCT", Currency.BTC, Currency.FCT),
	BTC_BTCD("BTC_BTCD", Currency.BTC, Currency.BTCD),
	BTC_DASH("BTC_DASH", Currency.BTC, Currency.DASH),
	BTC_NMC("BTC_NMC", Currency.BTC, Currency.NMC),
	BTC_PPC("BTC_PPC", Currency.BTC, Currency.PPC),
	BTC_STR("BTC_STR", Currency.BTC, Currency.STR),
	BTC_XBC("BTC_XBC", Currency.BTC, Currency.XBC),
	BTC_GNT("BTC_GNT", Currency.BTC, Currency.GNT),
	BTC_GNO("BTC_GNO", Currency.BTC, Currency.GNO),
	BTC_STRAT("BTC_STRAT", Currency.BTC, Currency.STRAT),
	BTC_NAV("BTC_NAV", Currency.BTC, Currency.NAV),
	BTC_OMNI("BTC_OMNI", Currency.BTC, Currency.OMNI),
	BTC_SC("BTC_SC", Currency.BTC, Currency.SC),
	BTC_BTS("BTC_BTS", Currency.BTC, Currency.BTS),
	BTC_SBD("BTC_SBD", Currency.BTC, Currency.SDB),
	BTC_LBC("BTC_LBC", Currency.BTC, Currency.LBC),
	BTC_BURST("BTC_BURST", Currency.BTC, Currency.BURST),
	BTC_BCN("BTC_BCN", Currency.BTC, Currency.BCN),
	BTC_BELA("BTC_BELA", Currency.BTC, Currency.BELA),
	BTC_BLK("BTC_BLK", Currency.BTC, Currency.BLK),
	BTC_GAME("BTC_GAME", Currency.BTC, Currency.GAME),
	BTC_NEOS("BTC_NEOS", Currency.BTC, Currency.NEOS),
	BTC_FLDC("BTC_FLDC", Currency.BTC, Currency.FLDC),
	BTC_MAID("BTC_MAID", Currency.BTC, Currency.MAID),
	BTC_POT("BTC_POT", Currency.BTC, Currency.POT),
	BTC_NOTE("BTC_NOTE", Currency.BTC, Currency.NOTE),
	BTC_PINK("BTC_PINK", Currency.BTC, Currency.PINK),
	BTC_SYS("BTC_SYS", Currency.BTC, Currency.SYS),
	BTC_VIA("BTC_VIA", Currency.BTC, Currency.VIA),
	BTC_VTC("BTC_VTC", Currency.BTC, Currency.VTC),
	BTC_XCP("BTC_XCP", Currency.BTC, Currency.XCP),
	BTC_CLAM("BTC_CLAM", Currency.BTC, Currency.CLAM),
	BTC_REP("BTC_REP", Currency.BTC, Currency.REP),
	BTC_NXC("BTC_NXC", Currency.BTC, Currency.NXC),
	BTC_STEEM("BTC_STEEM", Currency.BTC, Currency.STEEM),
	BTC_ETC("BTC_ETC", Currency.BTC, Currency.ETC),
	BTC_DCR("BTC_DCR", Currency.BTC, Currency.DCR),
	BTC_EXP("BTC_EXP", Currency.BTC, Currency.EXP),
	BTC_HUC("BTC_HUC", Currency.BTC, Currency.HUC),
	BTC_GRCL("BTC_GRCL", Currency.BTC, Currency.GRCL),
	BTC_NAUT("BTC_NAUT", Currency.BTC, Currency.NAUT),
	BTC_VRC("BTC_VRC", Currency.BTC, Currency.VRC),
	BTC_NXT("BTC_NXT", Currency.BTC, Currency.NXT),
	BTC_GRC("BTC_GRC", Currency.BTC, Currency.GRC),
	BTC_DGB("BTC_DGB", Currency.BTC, Currency.DGB),
	BTC_RIC("BTC_RIC", Currency.BTC, Currency.RIC),
	ETH_REP("ETH_REP", Currency.ETH, Currency.REP),
	ETH_GNT("ETH_GNT", Currency.ETH, Currency.GNT),
	ETH_ZEC("ETH_ZEC", Currency.ETH, Currency.ZEC),
	ETH_STEEM("ETH_STEEM", Currency.ETH, Currency.STEEM),
	ETH_GNO("ETH_GNO", Currency.ETH, Currency.GNO),
	ETH_ETC("ETH_ETC", Currency.ETH, Currency.ETC),
	ETH_LSK("ETH_LSK", Currency.ETH, Currency.LSK),
	XMR_BCN("XMR_BCN", Currency.XMR, Currency.BCN),
	XMR_ZEC("XMR_ZEC", Currency.XMR, Currency.ZEC),
	XMR_GNO("XMR_GNO", Currency.XMR, Currency.GNO),
	XMR_BTCD("XMR_BTCD", Currency.XMR, Currency.BTCD),
	XMR_DASH("XMR_DASH", Currency.XMR, Currency.DASH),
	XMR_MAID("XMR_MAID", Currency.XMR, Currency.MAID),
	XMR_NXT("XMR_NXT", Currency.XMR, Currency.NXT),
	XMR_BLK("XMR_BLK", Currency.XMR, Currency.BLK),
	XMR_LTC("XMR_LTC", Currency.XMR, Currency.LTC),
	USDT_REP("XMR_BTCD", Currency.USDT, Currency.REP),
	USDT_XRP("USDT_XRP", Currency.USDT, Currency.XRP),
	USDT_ETC("USDT_ETC", Currency.USDT, Currency.ETC),
	USDT_ZEC("USDT_ZEC", Currency.USDT, Currency.ZEC),
	USDT_LTC("USDT_LTC", Currency.USDT, Currency.LTC),
	USDT_STR("USDT_STR", Currency.USDT, Currency.STR),
	USDT_ETH("USDT_ETH", Currency.USDT, Currency.ETH),
	USDT_LSK("USDT_LSK", Currency.USDT, Currency.LSK),
	USDT_XMR("USDT_XMR", Currency.USDT, Currency.XMR),
	USDT_NXT("USDT_NXT", Currency.USDT, Currency.NXT),
	USDT_BTC("USDT_BTC", Currency.USDT, Currency.BTC),
	USDT_DASH("USDT_DASH", Currency.USDT, Currency.DASH);
	
	private final String code;
	private final Currency currency1;
	private final Currency currency2;

	private CurrencyPair(String code, Currency currency1, Currency currency2)
	{
		this.code = code;
		this.currency1 = currency1;
		this.currency2 = currency2;
	}

	@Override
	public String toString()
	{
		return currency2.getCode() + "/" + currency1.getCode();
	}

	public String getCode()
	{
		return code;
	}

	public Currency getCurrency1()
	{
		return currency1;
	}

	public Currency getCurrency2()
	{
		return currency2;
	}

}
