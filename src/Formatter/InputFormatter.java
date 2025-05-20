package Formatter;

import java.util.Scanner;

public class InputFormatter {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BOLD = "\u001B[1m";

    private static final Scanner scanner = new Scanner(System.in);

    // Wyświetla prompt i zwraca input użytkownika
    public static Scanner prompt(String message) {
        System.out.print(CYAN + "➤ " + message + ": " + RESET);
        return scanner;
    }

    // Wyświetla prompt i zwraca input użytkownika oraz czysci pozostaly tekst
    public static String promptAndClear(String message) {
        System.out.print(TextFormatter.CYAN + "➤ " + message + ": " + TextFormatter.RESET);
        String input = scanner.nextLine();

        // Tu można jeszcze "zatrzymać użytkownika", jeśli chcesz dać mu chwilę na zobaczenie odpowiedzi
        // System.out.println("Naciśnij Enter, aby kontynuować...");
        // scanner.nextLine();

        TextFormatter.clearScreen(); // czyści ekran PO wpisaniu i zatwierdzeniu
        return input;
    }

    // Prompt z komentarzem pomocniczym
    public static Scanner promptWithHint(String message, String hint) {
        System.out.println(YELLOW + "  ℹ " + hint + RESET);
        return prompt(message);
    }

    // Prompt dla ważnego inputa (np. potwierdzenie, hasło, itp.)
    public static String promptBold(String message) {
        System.out.print(BOLD + CYAN + "❯ " + message + ": " + RESET);
        return scanner.nextLine();
    }
}