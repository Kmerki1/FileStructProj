import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileObject implements AbstractFile {
    String name;
    File file;
    public FileObject(String name) {
        this.name = name;
        try {
            File f1 = new File(name);
            if (f1.createNewFile()) {
                System.out.println("File created: " + f1.getName());
                file = f1;
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

    public String getInfo(){
        return "File: " +name;
    }
    public void delete(){//deletes a file or folder in the directory
        if (file.delete()) { 
            System.out.println("Deleted the file: " + file.getName());
        }else{
            System.out.println("Failed to delete the file.");
        } 
    }



    public void ls() {
        System.out.println("File: " + name);
    }

    public void setName(String name) {
        this.name = name;
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

    // rename file; Ina
    public void rename(String newName) {
        String oldName = name;
        File oldFile = new File(name);
        File newFile = new File(newName);

        if (oldFile.renameTo(newFile)) {
            name = newName;
            System.out.println(oldName + " renamed to " + newName);
        } else {
            System.out.println("Failed to rename " + name + " to " + newName);
        }
    }
}