package test;

import java.math.BigDecimal;
import java.util.Map;

public class Holding {

    private String symbol;
    private long numOfShares;
    private BigDecimal cost;

// need to initialize number of shares and cost

    public Holding(String symbol) {
        this.symbol = symbol;
        this.numOfShares = 0;
        this.cost = new BigDecimal(0);
    }

    public String getSymbol() {
        return this.symbol;
    }

    public long getNumOfShares() {
        return this.numOfShares;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    // Increase the number of shares and recalculate cost per share
    public void addToHolding(long numOfShares, BigDecimal sharePrice) {
        BigDecimal curTotalCost = sharePrice.multiply(new BigDecimal(numOfShares));

        BigDecimal totalCost = this.cost.multiply(new BigDecimal(this.numOfShares));

        BigDecimal finalTotalCost = totalCost.add(curTotalCost);
        this.numOfShares += numOfShares;
        this.cost = finalTotalCost.divide(new BigDecimal(this.numOfShares));
    }

    // Reduce the number of shares and recalculate cost
    public void removeFromHolding(long numOfShares, BigDecimal sharePrice) {
        if (canSell(numOfShares)) {
            BigDecimal curTotalCost = sharePrice.multiply(new BigDecimal(numOfShares));

            BigDecimal totalCost = this.cost.multiply(new BigDecimal(this.numOfShares));

            BigDecimal finalTotalCost = totalCost.subtract(curTotalCost);
            this.numOfShares -= numOfShares;
            this.cost = finalTotalCost.divide(new BigDecimal(this.numOfShares));
        }
    }

    // Before a sell-trade can be carried out, call this method to determine
    // if there are enough shares.
    public boolean canSell(long numOfSharesToSell) {
        return numOfSharesToSell <= this.numOfShares;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Symbol: " + this.getSymbol() + " shares: " + this.getNumOfShares() + " avg cost: " + this.getCost());

        return sb.toString();
    }
}
