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
                    System.out.print("Enter the name of the " + fileType + ": ");
                    String name = scanner.nextLine();

                    if (fileType.equals("file")) {
                        FileObject file = new FileObject(name);
                        includedFiles.add(file);
                    } else if (fileType.equals("directory")) {
                        Directory directory = new Directory(name);
                        includedFiles.add(directory);
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
                        System.out.println("Listing files: ");
                        viewFiles();
                    } else if (viewType.equals("directories")) {
                        System.out.println("Listing directories: ");
                        viewDirectories();
                    }
                    break;
                case "delete":
                    System.out.println("Do you want to delete a file or a directory?");
                    System.out.print("Enter 'file' or 'directory': ");
                    String targetType = scanner.nextLine().toLowerCase();
                    if (!targetType.equals("file") && !targetType.equals("directory")) {
                        System.out.println("Invalid choice! Please enter 'file' or 'directory'. Thank you!");
                        break;
                    }
                
                    System.out.print("Enter the name of the " + targetType + " to delete: ");
                    String deleteTargetName = scanner.nextLine();

                    System.out.println("Included files: " + includedFiles); // Debug output

                
                    AbstractFile target = null;
                
                    for (AbstractFile file : includedFiles) {
                        if (file.getName().equals(deleteTargetName)) {
                            target = file;
                            break;
                        }
                    }
                
                    if (target == null) {
                        System.out.println("The specified file or directory was not found.");
                    } else {
                        if (targetType.equals("file")) {
                            target.delete();
                        } else if (targetType.equals("directory")) {
                            ((Directory) target).delete(); // Cast target to Directory and call delete() on it
                        }
                        includedFiles.remove(target);
                        System.out.println(targetType + " '" + deleteTargetName + "' deleted successfully!");
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

    private static void displayMenu() {
        System.out.println("Commands:");
        System.out.println("- create: Create files or folders");
        System.out.println("- view: View files or folders");
        System.out.println("- delete: Delete files or folders");
        System.out.println("- exit: Exit the program");
    }
}