import java.io.File;
import java.util.*;

public class Directory implements AbstractFile {
    String name;
    ArrayList<AbstractFile> includedFiles = new ArrayList<AbstractFile>();

    public Directory(String name) {
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
}