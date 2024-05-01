import java.io.File;

public class ViewFile {
    public static void main(String[] args) {
        // Get the current directory
        String currentDirectory = System.getProperty("user.dir");

        // Create a File object for the current directory
        File directory = new File(currentDirectory);

        // Get a list of all files and folders in the current directory
        File[] filesAndFolders = directory.listFiles();

        // Loop through the files and folders
        if (filesAndFolders != null) {
            for (File file : filesAndFolders) {
                // Check if it's a directory
                if (file.isDirectory()) {
                    System.out.println("Folder: " + file.getName());
                }
            }
        } else {
            System.err.println("Failed to list files and folders in the directory.");
        }
    }
}