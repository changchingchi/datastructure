package test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class mainEffectiveJava {


    public static void main(String[] args) throws InterruptedException {
//          double x = 15;
//          double y = 10;
//
//          System.out.println(operation.MINUS.apply(x, y));


        Profolio p = new Profolio();
        Trade t = new Trade("AAPL", TradeAction.BUY, new BigDecimal(550.00), 100, new Date());
        p.modifyPortfolio(t);
        System.out.println(p);

        if (p.canSell("AAPL", 50)) {
            t = new Trade("AAPL", TradeAction.SALE, new BigDecimal(580.00), 50, new Date());
            p.modifyPortfolio(t);
            System.out.println(p);
        }

        t = new Trade("INTC", TradeAction.BUY, new BigDecimal(21.00), 100, new Date());
        p.modifyPortfolio(t);
        System.out.println(p);

        System.out.println("Number of holdings in the portfolio: " + p.getNumberOfHoldings());

        String[] symbols = p.getHoldingSymbols();

        for (String s : symbols) {
            System.out.printf("%s: %s\n", s, p.getHolding(s));
        }
    }
}