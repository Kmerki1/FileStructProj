import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileObject implements AbstractFile {
    String name;

    File f1;

    public FileObject(String name) {
        this.name = name;
        try {
            f1 = new File(name);
            if (f1.createNewFile()) {
                System.out.println("File created: " + f1.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeTo() {
        try {
            FileWriter myWriter = new FileWriter(name);
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            myWriter.write(userInput);

            myWriter.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ls() {
        System.out.println("File: " + name);
    }

    @Override
    public String getName() {
        return name;
    }
}