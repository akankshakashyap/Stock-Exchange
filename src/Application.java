import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, BadDataException, InternalError {
        String[] files = {"/home/akanksha/IdeaProjects/SE-2/src/file1.txt", "/home/akanksha/IdeaProjects/SE-2/src/file2.txt"};
        FileReader reader = new FileReader(files);
        while (reader.hasNext()) {
            StockManager.processOrder(reader.next());
        }
    }
}
