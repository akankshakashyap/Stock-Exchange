import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class FileReader extends Thread{
    Scanner inputFile;
    FileReader(String filepath) {
        try {
            inputFile = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("Bad File Name!");
        }
        this.setName(filepath);
    }
    @Override
    public void run(){
        while (inputFile.hasNextLine()) {
            String temp = inputFile.nextLine();
            String[] arr = temp.split(" ");
            try {
                if (Objects.equals(arr[0], "Sleep")) Thread.sleep(Integer.parseInt(arr[1]));
                else {
                    StockManager.processOrder(new Order(arr));
                }
            } catch (Exception e) {
                System.out.println("Invalid Data");
                e.printStackTrace();
            }
        }
    }

}