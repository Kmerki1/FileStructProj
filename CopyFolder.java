import java.io.*;

public class CopyFolder {
    public static void main(String[] args) {
        // Source folder path (folder to be copied)
        String sourceFolderPath = "C:\\Users\\tagsp\\OneDrive\\Desktop\\work\\Towson_U\\Spring_2024\\COSC_439\\testfolder";

        // Destination folder path (where the folder will be copied)
        String destinationFolderPath = "C:\\Users\\tagsp\\OneDrive\\Desktop\\work\\Towson_U\\Spring_2024\\COSC_439";

        // Output the source folder path
        System.out.println("Source folder path: " + sourceFolderPath);

        // Output the destination folder path
        System.out.println("Destination folder path: " + destinationFolderPath);

        try {
            // Create a File object representing the source folder
            File sourceFolder = new File(sourceFolderPath);

            // Create a File object representing the destination folder
            File destinationFolder = new File(destinationFolderPath);

            // Output the copy process initiation
            System.out.println("Copying folder...");

            // Copy the source folder to the destination folder
            copyFolder(sourceFolder, destinationFolder);

            // Output the completion message
            System.out.println("Folder copied successfully.");
        } catch (IOException e) {
            // Output error message if an exception occurs
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to recursively copy a folder and its contents
    private static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        // If the source folder does not exist, return
        if (!sourceFolder.exists()) {
            return;
        }

        // If the destination folder does not exist, create it
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Get all files and folders in the source folder
        File[] files = sourceFolder.listFiles();

        if (files != null) {
            // Iterate through each file/folder
            for (File file : files) {
                // Create a File object representing the destination file/folder
                File destination = new File(destinationFolder, file.getName());

                if (file.isDirectory()) {
                    // If it's a directory, recursively copy it
                    copyFolder(file, destination);
                } else {
                    // If it's a file, copy it
                    copyFile(file, destination);
                }
            }
        }
    }

    // Method to copy a file
    private static void copyFile(File sourceFile, File destinationFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(sourceFile);
             OutputStream outputStream = new FileOutputStream(destinationFile)) {
            // Transfer bytes from the input stream to the output stream
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
