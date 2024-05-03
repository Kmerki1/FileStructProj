import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TerminalInterface {
    
    private static List<AbstractFile> includedFiles = new ArrayList<>();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Display initial message or prompt
        System.out.println("File System Interface");

        // Display menu options
        displayMenu();

        // Continue accepting commands until the user exits
        boolean running = true;
        while (running) {
            // Prompt user for input
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            // Process user command
            switch (command.toLowerCase()) {
                case "create":
                    System.out.println("Do you want to create a file or a directory?: ");
                    System.out.print("Enter 'file' or 'directory': ");
                    String fileType = scanner.nextLine().toLowerCase();

                    if (!fileType.equals("file") && !fileType.equals("directory")) {
                        System.out.println("Invalid choice! Please enter 'file' or 'directory' instead. Thank you!");
                        break;
                    }

                    if (fileType.equals("file")) {
                        System.out.print("Do you want to create the file within a directory? (y/n): ");
                        String createInDirectory = scanner.nextLine().toLowerCase();
                        
                        if (createInDirectory.equals("y")) {
                            System.out.print("Enter the name of the directory where you want to create the file: ");
                            String directoryName = scanner.nextLine();
                            File directory = new File(directoryName);
                            
                            if (!directory.exists()) {
                                System.out.println("Directory not found or invalid directory name.");
                                break;
                            }
                            
                            System.out.print("Enter the name of the file: ");
                            String fileName = scanner.nextLine();
                            File file = new File(directory, fileName);
                            
                            try {
                                if (file.createNewFile()) {
                                    System.out.println("File created: " + fileName + " in directory: " + directoryName);
                                } else {
                                    System.out.println("File already exists.");
                                }
                            } catch (IOException e) {
                                System.out.println("An error occurred while creating the file.");
                                e.printStackTrace();
                            }
                        } else {
                            System.out.print("Enter the name of the file: ");
                            String fileName = scanner.nextLine();
                            FileObject file = new FileObject(fileName);
                            includedFiles.add(file);
                        }
                    } else if (fileType.equals("directory")) {
                        System.out.print("Enter the name of the directory: ");
                        String name = scanner.nextLine();
                        Directory directory = new Directory(name);
                        includedFiles.add(directory);
                    }
                    break;
                case "write":
                    System.out.println("Enter the name of the file you want to write to: ");
                    String fileName = scanner.nextLine();

                    AbstractFile fileToWrite = null;
                    for (AbstractFile file : includedFiles) {
                        if (file.getName().equals(fileName)) {
                            fileToWrite = file;
                            break;
                        }
                    }

                    if (fileToWrite == null || !(fileToWrite instanceof FileObject)) {
                        System.out.println("File not found or it is not writable.");
                        break;
                    }

                    FileObject writableFile = (FileObject) fileToWrite;

                    System.out.println("Enter the content you want to write to the file (type 'done' to finish): ");
                    StringBuilder contentBuilder = new StringBuilder();
                    String line;

                    while (!(line = scanner.nextLine()).equalsIgnoreCase("done")) {
                        contentBuilder.append(line).append(System.lineSeparator());
                    }

                    writableFile.writeToFile(contentBuilder.toString());
                    break;
                case "search":
                    System.out.println("Enter the name of the file you want to search for: ");
                    String searchName = scanner.nextLine();
                    searchFile(searchName);
                    break;
                case "rename":
                    System.out.println("Enter the name of the file you want to rename: ");
                    String oldName = scanner.nextLine();
                    System.out.println("Enter the new name for the file: ");
                    String newName = scanner.nextLine();
                    
                    boolean nameFound = false;

                    for (AbstractFile file : includedFiles) {
                        if (file.getName().equalsIgnoreCase(oldName) && file instanceof FileObject) {
                            FileObject fileObject = (FileObject) file;
                            fileObject.rename(newName);
                            nameFound = true;
                            break;
                        }
                    }
                    if (!nameFound) {
                        System.out.println("File not found or cannot be renamed.");
                    }
                    break;
                case "view":
                    System.out.println("Do you want to view files or directories?");
                    System.out.print("Enter 'files' or 'directories': ");
                    String viewType = scanner.nextLine().toLowerCase();

                    if (!viewType.equals("files") && !viewType.equals("directories")) {
                        System.out.println("Invalid choice! Please enter 'files' or 'directories'. Thank you!");
                        break;
                    }

                    if (viewType.equals("files")) {
                        viewFiles();
                    } else if (viewType.equals("directories")) {
                        System.out.println("Listing directories: ");
                        viewDirectories();
                    }
                    break;
                case "delete":
                    System.out.print("Enter the name of the file or directory you want to delete: ");
                    String deleteTarget = scanner.nextLine();

                    boolean found = false;

                    for (AbstractFile file : includedFiles) {
                        if (file.getName().equals(deleteTarget)) {
                            file.delete();
                            includedFiles.remove(file);
                            found = true;
                            System.out.println(deleteTarget + " deleted successfully!");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("File or directory not found.");
                    }
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command! Please try again.");
            }
        }

        // Close scanner
        scanner.close();
    }

    private static void viewFiles() {
        File currentDirectory = new File(".");

        File[] files = currentDirectory.listFiles();
        System.out.println("Listing files: ");
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    private static void viewDirectories() {
        File currentDirectory = new File(".");

        File[] files = currentDirectory.listFiles();
        System.out.println("Listing directories:");
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
    }

    // searching for files
    private static void searchFile(String name) {
        boolean found = false;

        for (AbstractFile file : includedFiles) {
            if (file.getInfo().equalsIgnoreCase("File: " + name)) {
                System.out.println("File found: " + name);
                found = true;
                break;
            } else if (file instanceof Directory) {
                Directory directory = (Directory) file;
                for (AbstractFile innerFile : directory.getIncludedFiles()) {
                    if (innerFile.getInfo().equalsIgnoreCase("File: " + name)) {
                        System.out.println("File found: " + name + " found in " + directory.getName());
                        found = true;
                        break;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("File not found: " + name);
        }
    }

    /*
    // function to rename file
    private static void renameFile(String oldName, String newName) {
        boolean found = false;

        for (AbstractFile file : includedFiles) {
            if (file.getName().equalsIgnoreCase(oldName)) {
                File oldFile = new File(oldName);
                File newFile = new File(newName);

                if (oldFile.renameTo(newFile)) {
                    file.setName(newName);
                    System.out.println(oldName + " renamed to " + newName);
                    found = true;
                } else {
                    System.out.println("Failed to rename " + oldName + " to " + newName);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("File not found: " + oldName);
        }
    }
    */

    // function to check if the file exists
    private static boolean fileExists(String name) {
        for (AbstractFile file : includedFiles) {
            if (file.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private static void displayMenu() {
        System.out.println("Commands:");
        System.out.println("- create: Create files or folders");
        System.out.println("- write: Write to files");
        System.out.println("- search: Search for files");
        System.out.println("- rename: Rename a file");
        System.out.println("- view: View files or folders");
        System.out.println("- delete: Delete files or folders");
        System.out.println("- exit: Exit the program");
    }
}

