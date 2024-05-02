import java.io.File;
import java.util.*;

public class Directory implements AbstractFile {
    String name;
    ArrayList<AbstractFile> includedFiles = new ArrayList<AbstractFile>();

    public Directory(String name) {
        this.name = name;
        File f1 = new File(name);
        boolean bool = f1.mkdir();
        if (bool) {
            System.out.println("Directory created: " + name);
        } else {
            System.out.println("Directory "+ name +" already exists");
        }
    }

    public void ls() {
        System.out.println("Directory: " + name);
        for (int i = 0; i < includedFiles.size(); i++) {
            includedFiles.get(i).ls();
        }
    }

    public void add(AbstractFile file) {
        includedFiles.add(file);
    }
    public String getName(){
        return name;
    }


    // this method is used to delete items within a parent directory
    public void deleteTarget(String target) { // calls the proper delete function on an AbstractFile within THIS directory, searched by name
        for (int i = 0; i < includedFiles.size(); i++) {
            if (includedFiles.get(i).getName() == target) {
                includedFiles.get(i).delete();
                includedFiles.remove(i);  //removes the target from this directories array
            }
        }
    }
    
    //this method is what is called by parent directories for deletion
    public void delete() {  //called when PARENT directories delete this directory, deletes the directory and EVERYTHING within it
        for (int i = 0; i < includedFiles.size(); i++) {
            includedFiles.get(i).delete();
        }

        includedFiles.remove(this);
        File directory = new File(name);
        if (directory.exists() && directory.isDirectory()) {
            directory.delete();
        }
        /*
        //includedFiles.clear(); //deletes the array references to all objects in this directory
        includedFiles.delete(); //delete the directory itself
        */
    }

    public ArrayList<AbstractFile> getIncludedFiles() {
        return includedFiles;
    }
}