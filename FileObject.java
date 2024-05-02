import java.io.File;
import java.io.IOException;

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
}