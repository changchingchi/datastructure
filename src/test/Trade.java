package test;

import java.math.BigDecimal;
import java.util.Date;

public class Trade {
    public String getSymbol() {
        return symbol;
    }

    public TradeAction getAction() {
        return action;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    private String symbol;
    private TradeAction action; // buy or sell
    private BigDecimal price; // Use BigDecimal for currency
    private int numOfShares;
    private Date tradeDate;


    public Trade(String symbol, TradeAction action, BigDecimal price, int numOfShares, Date tradeDate) {
        this.symbol = symbol;
        this.action = action;
        this.price = price;
        this.tradeDate = tradeDate;
        this.numOfShares = numOfShares;
    }

}
