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