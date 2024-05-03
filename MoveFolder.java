import java.io.*;

public class MoveFolder {
    public static void main(String[] args) {
        // Source folder name (folder to be moved)
        String sourceFolderName = "C:\\Users\\tagsp\\OneDrive\\Desktop\\work\\Towson_U\\Spring_2024\\COSC_439\\testfolder";

        // Destination folder path (where the folder will be moved)
        String destinationFolderPath = "C:\\Users\\tagsp\\OneDrive\\Desktop\\work\\Towson_U\\Spring_2024\\COSC 439\\project\\FileStructProj";

        // Output the source folder name
        System.out.println("Source folder name: " + sourceFolderName);

        // Output the destination folder path
        System.out.println("Destination folder path: " + destinationFolderPath);

        try {
            // Create a File object representing the source folder
            File sourceFolder = new File(sourceFolderName);

            // Create a File object representing the destination folder
            File destinationFolder = new File(destinationFolderPath);

            // Output the move process initiation
            System.out.println("Moving folder...");

            // Move the source folder to the destination folder
            moveFolder(sourceFolder, destinationFolder);

            // Output the completion message
            System.out.println("Folder moved successfully.");
        } catch (IOException e) {
            // Output error message if an exception occurs
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to move a folder
    private static void moveFolder(File sourceFolder, File destinationFolder) throws IOException {
        // If the source folder does not exist, return
        if (!sourceFolder.exists()) {
            System.out.println("Source folder does not exist.");
            return;
        }

        // If the destination folder does not exist, create it
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Output the source folder path
        System.out.println("Source folder path: " + sourceFolder.getAbsolutePath());

        // Output the destination folder path
        System.out.println("Destination folder path: " + destinationFolder.getAbsolutePath());

        // Move the source folder to the destination folder
        boolean success = sourceFolder.renameTo(new File(destinationFolder, sourceFolder.getName()));

        // Output the result of the move operation
        if (success) {
            System.out.println("Move operation successful.");
        } else {
            System.out.println("Move operation failed.");
        }
    }
}
