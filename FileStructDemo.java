public class FileStructDemo{
    //Kyle Merkins
    public static void main(String[] args){
        
        Directory test = new Directory("root");
        test.ls();//shows just the root currently
        System.out.println();//spacer

        Directory test2 = new Directory("subDirectory1");
        test.add(test2);
        FileObject testFile = new FileObject("testFile.txt");
        test.add(testFile);
        test.ls();//shows root, a subdirectory, and a file
        System.out.println(test.search("testfile.txt"));
        System.out.println();//spacer

        test.delete();
        test.ls();
    }
}