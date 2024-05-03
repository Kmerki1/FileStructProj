import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileObject implements AbstractFile {
    String name;

    public FileObject(String name) {
        this.name = name;
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

    public String getName(){
        return name;
    }

    public void delete() {
        File file = new File(name);
        if (file.delete()) {  //deletes a file or folder in the directory
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public void ls() {
        System.out.println("File: " + name);
    }

    // write to file; Ina
    public void writeToFile(String content) {
        try (FileWriter writer = new FileWriter(name, true)) {
            writer.write(content);
            writer.write(System.lineSeparator());
            System.out.println("Content written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while trying to write to the file.");
            e.printStackTrace();
        }
    }
}