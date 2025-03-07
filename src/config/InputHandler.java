package config;

import java.util.Scanner;

/**
 * Handles user input, providing methods to read strings and yes/no answers.
 */
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a line from standard input, trims whitespace, and returns it.
     * @return the user's input as a trimmed string
     */
    public static String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Asks a yes/no question to the user and validates the response.
     * @param prompt the question to display
     * @return true if the user answers 'y', false if 'n'
     */
    public static boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String response = getInput().toLowerCase();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid response. Please enter Y or N.");
            }
        }
    }
}
