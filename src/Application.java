import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        String[] files = {"src/file1.txt", "src/file2.txt"};
        for(String f : files){
            new FileReader(f).start();
        }
    }
}
