public class File implements AbstractFile{
    String name;
    public File(String name){
        this.name = name;
    }
    
    
    
    public void ls(){
        System.out.println("File: " + name);
    }
}