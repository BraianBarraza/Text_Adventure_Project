package config;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prevent instantiation
     */
    public static String getInput() {
        return scanner.nextLine().trim();
    }


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

    public static void closeScanner() {
        scanner.close();
    }
}

