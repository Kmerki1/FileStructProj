import java.io.File;
import java.io.FileNotFoundException;
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

    public void read(){
        try{
            Scanner reader = new Scanner(f1);
            while(reader.hasNextLine()){
                String text = reader.nextLine();
                System.out.println(text);
            }
            reader.close();
        }catch (Exception e){
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
        if (f1.delete()) { 
            System.out.println("Deleted the file: " + f1.getName());
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