import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class FileReader {
    String[] files;
    Order curOrder;
    int fileIndex; // where in files array we are
    Scanner inputFile;

    FileReader(String @NotNull [] files) throws FileNotFoundException {
        this.files = files;
        fileIndex = 0;
        try {
            inputFile = new Scanner(new File(files[fileIndex]));
        } catch (FileNotFoundException e) {
            System.out.println("Bad File Name!");
        }
    }

    public boolean hasNext() throws FileNotFoundException, InterruptedException, BadDataException {
        if (curOrder != null) return true;
        while (fileIndex < files.length) {
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
                }
            }
            try {
                if (++fileIndex < files.length) inputFile = new Scanner(new File(files[fileIndex]));
            } catch (FileNotFoundException e) {
                System.out.println("Bad File Name!");
            }
        }
        return false;
    }

    public Order next() {
        Order temp = curOrder;
        curOrder = null;
        return temp;
    }

}