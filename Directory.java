import java.io.File;
import java.util.*;

public class Directory implements AbstractFile {
    String name;
    ArrayList<AbstractFile> includedFiles = new ArrayList<AbstractFile>();
    File directory;

    public Directory(String name) {
        this.name = name;
        File f1 = new File(name);
        boolean bool = f1.mkdir();
        if (bool) {
            System.out.println("Directory created: " + name);
            directory = f1;
        } else {
            System.out.println("Directory "+ name +" already exists");
        }
    }

    public void ls() {
        System.out.println("Current Directory: " + name);
        for (int i = 0; i < includedFiles.size(); i++) {
            System.out.println(includedFiles.get(i).getInfo());
        }
    }

    public void add(AbstractFile file) {
        includedFiles.add(file);
    }
    public String getName(){
        return name;
    }
    public String getInfo(){//what gets returned when parent directory calls ls()
        return "Sub-directory: " + name;
    }

    //this method is used to delete items within a parent directory
    public void deleteTarget(String target){//calls the proper delete function on an AbstractFile within THIS directory, searched by name
        for(int i = 0; i<includedFiles.size(); i++){
            if(includedFiles.get(i).getName().equalsIgnoreCase(target)){
                includedFiles.get(i).delete();
                includedFiles.remove(i);//removes the target from this directories array
            }
        }

    }
    //this method is what is called by parent directories for deletion
    public void delete(){//called when PARENT directories delete this directory, deletes the directory and EVERYTHING within it
        for(int i = 0; i<includedFiles.size(); i++){
            includedFiles.get(i).delete();//deletes ALL objects within this directory if PARENT directory calls deleteTarget with this directory as target
        }
        includedFiles.clear();//deletes the array references to all objects in this directory
        directory.delete();//delete the directory itself
        System.out.println("Directoy: " + name + " deleted");
    }

    public boolean search(String target){//simply returns true if target is found or false otherwise
        for(int i = 0; i<includedFiles.size(); i++){
            if(includedFiles.get(i).getName().equalsIgnoreCase(target)){
                return true;
            }
        }
        return false;
    }
    public AbstractFile getFile(String target){//returns the whole fileObject or Directory, null if not found, should be used after search returns true
        for(int i = 0; i<includedFiles.size(); i++){
            if(includedFiles.get(i).getName().equalsIgnoreCase(target)){
                return includedFiles.get(i);
            }
        }
        return null;
    }
}