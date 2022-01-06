import java.util.Objects;

public class Order {
    String stockName;
    int price;
    String tradingPartyName;
    String direction;
    String type;

    Order(String[] arr) throws BadDataException {
        int valid = 1;
        try {
            if (Integer.parseInt(arr[1]) < 0) {
                valid = 0;
            }
            if (!Objects.equals(arr[3], "Buy") && !Objects.equals(arr[3], "Sell")) {
                valid = 0;
            }
            if (!Objects.equals(arr[4], "Market") && !Objects.equals(arr[4], "Limit")
                    && !Objects.equals(arr[4], "IOC")) {
                valid = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadDataException();

        }
        if (valid == 0) {
            throw new BadDataException();
        }
        stockName = arr[0];
        price = Integer.parseInt(arr[1]);
        tradingPartyName = arr[2];
        direction = arr[3];
        type = arr[4];
    }

    public void print() {
        System.out.println(price + " " + type + " " + stockName
                + " " + direction);
    }
}
