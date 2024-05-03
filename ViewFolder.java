import java.io.File;

public class ViewFolder{
    public static void main(String[] args) {
        // Get the current directory
        File currentDirectory = new File(System.getProperty("user.dir"));

        // List all files and folders in the current directory
        File[] filesAndFolders = currentDirectory.listFiles();

        if (filesAndFolders != null) {
            // Loop through each file/folder and check if it's a directory
            for (File folder : filesAndFolders) {
                if (folder.isDirectory()) {
                    // Output the folder name
                    System.out.println("Folder: " + folder.getName());
                }
            }
        } else {
            System.out.println("Failed to list files and folders in the current directory.");
        }
    }
}