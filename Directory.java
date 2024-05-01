import java.io.File;
import java.io.IOException;
import java.util.*;
public class Directory implements AbstractFile{
    String name;
    ArrayList<AbstractFile> includedFiles = new ArrayList<AbstractFile>();
    public Directory(String name){
        File f1 = new File(name);  
      //Creating a folder using mkdir() method  
      boolean bool = f1.mkdir();  
      if(bool){  
         System.out.println("Directory created: " + name);  
      }else{  
         System.out.println("Error Found!");  
      }  
    }

    public void ls(){
        System.out.println("Directory: " + name);
        for(int i = 0; i < includedFiles.size(); i++){
            includedFiles.get(i).ls();
        }
    }
    public void add(AbstractFile file){
        includedFiles.add(file);
    }
}