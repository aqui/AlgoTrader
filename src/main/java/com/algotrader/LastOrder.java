package com.algotrader;

public class LastOrder 
{
	private String orderUuid;
	private String exchange;
	private String timeStamp;
	private String orderType;
	private double limit;
	private double quantity;
	private double quantityRemaining;
	private double commission;
	private double price;
	private double pricePerUnit;
	private boolean isConditional;
	private String condition;
	private String conditionTarget;
	private boolean immediateOrCancel;
	
	public String getOrderUuid() {
		return orderUuid;
	}
	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getQuantityRemaining() {
		return quantityRemaining;
	}
	public void setQuantityRemaining(double quantityRemaining) {
		this.quantityRemaining = quantityRemaining;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public boolean isConditional() {
		return isConditional;
	}
	public void setConditional(boolean isConditional) {
		this.isConditional = isConditional;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getConditionTarget() {
		return conditionTarget;
	}
	public void setConditionTarget(String conditionTarget) {
		this.conditionTarget = conditionTarget;
	}
	public boolean isImmediateOrCancel() {
		return immediateOrCancel;
	}
	public void setImmediateOrCancel(boolean immediateOrCancel) {
		this.immediateOrCancel = immediateOrCancel;
	}
}
