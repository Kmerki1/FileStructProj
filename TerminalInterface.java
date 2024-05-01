import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class TerminalInterface {
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
                    } else if (fileType.equals("directory")) {
                        Directory directory = new Directory(name);
                    }
                    break;
                case "view":
                    break;
                case "delete":
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

    private static void displayMenu() {
        System.out.println("Commands:");
        System.out.println("- create: Create files or folders");
        System.out.println("- view: View files or folders");
        System.out.println("- delete: Delete files or folders");
        System.out.println("- exit: Exit the program");
    }
}