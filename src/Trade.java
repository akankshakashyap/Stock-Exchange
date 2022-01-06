import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Trade {
    int price;
    String stockName;
    String buyerParty;
    int buyerPartyPrice;
    int sellerPartyPrice;
    String sellerParty;

    public Trade(@NotNull Order a, @NotNull Order b) throws InternalError {

        price = (a.price + b.price) / 2;
        if (Objects.equals(a.stockName, b.stockName)) {
            stockName = a.stockName;
        } else {
            throw new InternalError();
        }
        if (Objects.equals(a.direction, "Sell") && Objects.equals(b.direction, "Buy")) {
            sellerParty = a.tradingPartyName;
            sellerPartyPrice = a.price;
            buyerParty = b.tradingPartyName;
            buyerPartyPrice = b.price;
        } else if (Objects.equals(b.direction, "Sell") && Objects.equals(a.direction, "Buy")) {
            sellerParty = b.tradingPartyName;
            sellerPartyPrice = b.price;
            buyerParty = a.tradingPartyName;
            buyerPartyPrice = a.price;
        } else {
            throw new InternalError();
        }
        print();
    }

    public void print() {
//        System.out.println(Thread.currentThread().getName());
        System.out.println("Trade: " + stockName + "\n" + "Parties involved:\n" + "Buyer Party: " + buyerParty +
                " (" + buyerPartyPrice + ")\n" + "Seller Party: " + sellerParty + " (" + sellerPartyPrice + ")\n"+
        "Price at which trade took palace: "+ price +"\n");
    }
}
