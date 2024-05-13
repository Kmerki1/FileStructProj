import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class FileObject implements AbstractFile {
    String name;

    File f1;

    public FileObject(String name, Directory currDir) {
        this.name = name;
        try {
            f1 = new File(currDir.directory.getAbsolutePath() + "\\"+ name);
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

    public void writeTo(Scanner input) {
        try {
            FileWriter myWriter = new FileWriter(name);
            //Scanner input = new Scanner(System.in);
            System.out.println("Enter what you want to write: ");
            String userInput = input.nextLine();

            myWriter.write(userInput);

            myWriter.close();
            //input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public String getInfo(){
        return "File: " +name + "\n FilePath: " + f1.getAbsolutePath();
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
    public void rename(String newName, Directory currDir) {
        
        f1.renameTo(new File(currDir.directory.getAbsolutePath() + "\\"+ newName));
        this.name = newName;
        System.out.println("Renamed successfully");
    }    
}