import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public class StockManager {
    private static final HashMap<String, HashMap<Boolean, PriorityQueue<Order>>> stockExchange = new HashMap<>();
    private static final ArrayList<Trade> tradeHistory = new ArrayList<Trade>();
    private static final HashMap<String, Quote> quotes = new HashMap<String, Quote>();

    synchronized private static void addStock(String stockname) {
        HashMap<Boolean, PriorityQueue<Order>> temp = new HashMap<>();
        temp.put(false, new PriorityQueue<>((a, b) -> b.price - a.price)); // buy = 0;
        temp.put(true, new PriorityQueue<>((a, b) -> a.price - b.price)); // sell = 1;
        stockExchange.putIfAbsent(stockname, temp);
    }

    synchronized public static void processOrder(@NotNull Order order) throws IOException, InternalError {
        quotes.putIfAbsent(order.stockName, new Quote(order.stockName));
        quotes.get(order.stockName).updateQuote(order);
        addStock(order.stockName);
        boolean direction = Objects.equals(order.direction, "Sell");
        Order cross = stockExchange.get(order.stockName).get(!direction).peek();
        boolean valid = (cross != null);
        if (valid && Objects.equals(order.type, "Limit")) {
            if (direction) { // dirn == sell
                if (order.price < cross.price) {
                    valid = false;
                }
            } else { // dirn == buy
                if (order.price > cross.price) {
                    valid = false;
                }
            }
        }
        if (valid) {
            Trade cur = new Trade(order, cross);
            tradeHistory.add(cur);
            Order temp = stockExchange.get(order.stockName).get(!direction).poll();
            if(temp == null) System.out.println("hm");
        } else if (!Objects.equals(order.type, "IOC")) {
            stockExchange.get(order.stockName).get(direction).add(order);
        }
//        printSE();
    }

    synchronized public static void printSE() {
        for (Map.Entry<String, HashMap<Boolean, PriorityQueue<Order>>> outside : stockExchange.entrySet()) {
            for (Map.Entry<Boolean, PriorityQueue<Order>> inside : outside.getValue().entrySet()) {
                System.out.println(outside.getKey() + " " + inside.getKey());
                for (Order order : inside.getValue()) {
                    System.out.print(order.price + " " + order.type + ", ");
                }
                System.out.print("\n");
            }

        }
    }
}
