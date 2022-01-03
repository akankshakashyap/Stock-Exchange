import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Quote {
    String stockName;
    int bestBuyPrice;
    int bestSellingPrice;
    File result = new File("/home/akanksha/IdeaProjects/SE-2/src/updatedQuote.txt");

    Quote(String s) {
        stockName = s;
        bestBuyPrice = 0;
        bestSellingPrice = 10000000;
    }

    public void updateQuote(@NotNull Order order) throws IOException {
        String s = order.direction;
        int price = order.price;
        if (Objects.equals(s, "Buy")) {
            if (price > bestBuyPrice) {
                bestBuyPrice = price;
                dumpQuote();
            }
        } else {
            if (price < bestSellingPrice) {
                bestSellingPrice = price;
                dumpQuote();
            }
        }

    }

    public void dumpQuote() throws IOException {
        String filecontent = "Stockname : " + stockName + "\nBest buy price : "
                + (bestBuyPrice == 0 ? "None" : bestBuyPrice)
                + " \nBest selling price : " + (bestSellingPrice == 10000000 ? "None" : bestSellingPrice) + "\n\n";
        Files.writeString(Path.of("/home/akanksha/IdeaProjects/SE-2/src/updatedQuote.txt"), filecontent, CREATE,
                APPEND);
    }
}
