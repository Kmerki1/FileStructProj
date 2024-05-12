import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "create":
                    System.out.println("Do you want to create a file or a directory?");
                    System.out.print("Enter 'file' or 'directory': ");
                    String fileType = scanner.nextLine().toLowerCase();

                    if (!fileType.equals("file") && !fileType.equals("directory")) {
                        System.out.println("Invalid choice! Please enter 'file' or 'directory' instead. Thank you!");
                        break;
                    }

                    if (fileType.equals("file")){
                        System.out.print("Enter the name of the file: ");
                        String fileName = scanner.nextLine();
                        FileObject file = new FileObject(fileName);
                        includedFiles.add(file);
                        
                    }else if (fileType.equals("directory")) {
                        System.out.print("Enter the name of the directory: ");
                        String name = scanner.nextLine();
                        Directory directory = new Directory(name);
                        includedFiles.add(directory);
                    }
                    break;

                case "read":
                System.out.println("Enter the name of the file you want to read (must include '.txt'): ");
                String fName = scanner.nextLine();

                AbstractFile fileToRead = null;
                for (AbstractFile file : includedFiles) {
                    if (file.getName().equals(fName)) {
                        fileToRead = file;
                        break;
                    }
                }

                if (fileToRead == null || !(fileToRead instanceof FileObject)) {
                    System.out.println("File not found or it is not readable.");
                    break;
                }
                ((FileObject) fileToRead).read();
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
                    /*
                    System.out.println("Enter the content you want to write to the file (type 'done' to finish): ");
                    StringBuilder contentBuilder = new StringBuilder();
                    String line;

                    while (!(line = scanner.nextLine()).equalsIgnoreCase("done")) {
                        contentBuilder.append(line).append(System.lineSeparator());
                    }
                    */
                    writableFile.writeTo(); //was param = contentBuilder.toString()
                    break;
                case "search":
                    System.out.print("Enter the name of the file or directory you want to search for: ");
                    String searchTarget = scanner.nextLine();

                    boolean searchFound = false;

                    for (AbstractFile file : includedFiles) {
                        if (file.getName().equalsIgnoreCase(searchTarget)) {
                            System.out.println("Found: " + file.getInfo());
                            searchFound = true;
                        } else if (file instanceof Directory) {
                            Directory directory = (Directory) file;
                            if (directory.search(searchTarget)) {
                                System.out.println("Found in directory " + directory.getName() + ": " + file.getInfo());
                                searchFound = true;
                            }
                        }
                    }

                    if (!searchFound) {
                        System.out.println("File or directory not found.");
                    }
                    break;
                case "rename":
                    System.out.print("Enter the name of the file you want to rename: ");
                    String oldName = scanner.nextLine();
                    System.out.print("Enter the new name for the file: ");
                    String newName = scanner.nextLine();
                    
                    boolean renamed = false;

                    for (AbstractFile file : includedFiles) {
                        if (file.getName().equalsIgnoreCase(oldName)) {
                            if (file instanceof FileObject) {
                                FileObject fileObject = (FileObject) file;
                                fileObject.rename(newName);
                            } else if (file instanceof Directory) {
                                Directory directory = (Directory) file;
                                directory.rename(newName);
                            }
                        }
                        renamed = true;
                    }
                    if (!renamed) {
                        System.out.println("File not found or directory not found.");
                    }
                    break;
                case "view":
                    // To implement
                    break;
                case "delete":
                    // Delete switch case; works fine, I think
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

        // close the scanner
        scanner.close();
    }
    
    // this is the display menu for the user to select from the switch cases; Ina
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

