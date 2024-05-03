public interface AbstractFile{
    public void ls();
    String getName();
    String getInfo(); // used in ls() to print directory item information
    void delete();
}