public class FileStructDemo{
    //Kyle Merkins
    public static void main(String[] args){
        Directory test = new Directory("root");
        test.ls();//shows just the root currently
        System.out.println();//spacer

        Directory test2 = new Directory("subDirectory1");
        test.add(test2);
        File testFile = new File("testFile");
        test.add(testFile);
        test.ls();//shows root, a subdirectory, and a file
        System.out.println();//spacer

        test.add(new Directory("Subdirectory2"));
        test2.add(new File("subDirectory1's file"));
        test.ls();//shows root, a subdirectory and its inner file, a file and another directory
    }
}