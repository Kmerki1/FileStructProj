import java.util.*;
public class Directory implements AbstractFile{
    String name;
    ArrayList<AbstractFile> includedFiles = new ArrayList<AbstractFile>();
    public Directory(String name){
        this.name = name;
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