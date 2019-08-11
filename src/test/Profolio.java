package test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Profolio {
    //symbol vs trade object
    private HashMap<String, Holding> holdings;

    public Profolio() {
        holdings = new HashMap<>();
    }

    public int getNumberOfHoldings() {
        return holdings.size();
    }

    public String[] getHoldingSymbols() {
        String[] symbols = new String[holdings.size()-1];

        Iterator itr = holdings.entrySet().iterator();

        for (int i = 0; itr.hasNext(); ) {
            symbols[i] = ((Map.Entry<String, Holding>) itr.next()).getKey();
        }
        return symbols;
    }

    public Holding getHolding(String symbol) {
        return holdings.get(symbol);
    }

    // Use the information in a trade to update the corresponding holding in the portfolio.
    // Note: a trade can a buy or a sell.
    public void modifyPortfolio(Trade trade) {
        Holding curTrade = new Holding(trade.getSymbol());

        if(holdings.containsKey(trade.getSymbol())){
            curTrade = holdings.get(trade.getSymbol());
        }

        if(trade.getAction() == TradeAction.BUY){
            curTrade.addToHolding(trade.getNumOfShares(), trade.getPrice());
        }else{
            curTrade.removeFromHolding(trade.getNumOfShares(), trade.getPrice());
        }
        holdings.put(trade.getSymbol(), curTrade);
    }

    // A client should call this method to determine if the portfolio has the corresponding holding
    // and enough shares
    public boolean canSell(String symbol, long numOfSharesToSell) {
        Holding holding = holdings.get(symbol);
        return holding.getNumOfShares() >= numOfSharesToSell;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Holding> e : holdings.entrySet()) {
            Holding temp = e.getValue();
            sb.append("Symbol: " + temp.getSymbol() + " shares: " + temp.getNumOfShares() + " avg cost: " + temp.getCost());
        }

        return sb.toString();
    }
}
