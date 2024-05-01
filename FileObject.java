import java.io.File;
import java.io.IOException;

public class FileObject implements AbstractFile {
    String name;

    public FileObject(String name) {
        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ls() {
        System.out.println("File: " + name);
    }
}