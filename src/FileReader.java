import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FileReader extends Thread{
    Order curOrder;
    Scanner inputFile;

    FileReader(String filepath) throws FileNotFoundException {
        try {
            inputFile = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("Bad File Name!");
        }
        this.setName(filepath);
    }

    public boolean hasNext() throws FileNotFoundException, InterruptedException, BadDataException {
        while (inputFile.hasNextLine()) {
            String temp = inputFile.nextLine();
            String[] arr = temp.split(" ");
            try {
                if (Objects.equals(arr[0], "Sleep")) Thread.sleep(Integer.parseInt(arr[1])); //general exp
                else {
                    curOrder = new Order(arr); //custom exp
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Invalid Data");
                e.printStackTrace();
            }
        }
        return false;
    }

    public Order next() {
        Order temp = curOrder;
        curOrder = null;
        return temp;
    }
    @Override
    public void run(){
        while(true){
            try {
                if (!this.hasNext()) break;
                StockManager.processOrder(this.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}