package com.algotrader.currency;

public enum Currency {

	BTC("BTC", "Bitcoin"), 
	ETH("ETH", "Ethereum"), 
	XEM("XEM", ""),
	USDT("USDT", ""),
	XRP("XRP", ""),
	REP("REP", ""),
	ZEC("ZEC", ""),
	AMP("AMP", ""),
	XMR("XMR", ""),
	DASH("DASH", ""),
	PASC("PASC", ""),
	LSK("LSK", ""),
	XVC("XVC", ""),
	XPM("XPM", ""),
	ARDR("ARDR", ""),
	BCY("BCY", ""),
	RADS("RADS", ""),
	DOGE("DOGE", ""),
	FLO("FLO", ""),
	LTC("LTC", ""),
	SJCX("SJCX", ""),
	FCT("FCT", ""),
	BTCD("BTCD", ""),
	NMC("NMC", ""),
	PPC("PPC", ""),
	STR("STR", ""),
	XBC("XBC", ""),
	GNT("GNT", ""),
	GNO("GNO", ""),
	STRAT("STRAT", ""),
	NAV("NAV", ""),
	OMNI("OMNI", ""),
	SC("SC", ""),
	BTS("BTS", ""),
	SDB("SDB", ""),
	LBC("LBC", ""),
	BURST("BURST", ""),
	BCN("BCN", ""),
	BELA("BELA", ""),
	BLK("BLK", ""),
	GAME("GAME", ""),
	NEOS("NEOS", ""),
	FLDC("FLDC", ""),
	MAID("MAID", ""),
	POT("POT", ""),
	NOTE("NOTE", ""),
	PINK("PIN", ""),
	SYS("SYS", ""),
	VIA("VIA", ""),
	VTC("VTC", ""),
	XCP("XCP", ""),
	CLAM("CLAM", ""),
	NXC("NXC", ""),
	ETC("ETC", ""),
	NXT("NXT", ""),
	DCR("DCR", ""),
	EXP("EXP", ""),
	HUC("HUC", ""),
	GRCL("GRCL", ""),
	NAUT("NAUT", ""),
	VRC("VRC", ""),
	GRC("GRC", ""),
	DGB("DGB", ""),
	RIC("RIC", ""),
	STEEM("STEEM", "");

	private final String code;
	private final String name;

	private Currency(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return code;
	}

}
