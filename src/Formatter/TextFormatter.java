package Formatter;

public class TextFormatter {

    // ANSI kolory (opcjonalnie)
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    // Tworzy obramowaną ramkę wokół tekstu
    public static String boxed(String text) {
        String[] lines = text.split("\n");
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, stripAnsi(line).length());
        }

        StringBuilder box = new StringBuilder();
        String border = BLUE + "+" + "-".repeat(maxLength + 2) + "+" + RESET + "\n";
        box.append(border);
        for (String line : lines) {
            box.append(BLUE + "| " + RESET)
                    .append(padAnsiRight(line, maxLength))
                    .append(BLUE + " |" + RESET + "\n");
        }
        box.append(border);
        return box.toString();
    }

    // Tworzy kolorowy nagłówek
    public static String header(String title) {
        return BOLD + BLUE + "\n=== " + title.toUpperCase() + " ===\n" + RESET;
    }

    // Tworzy linię oddzielającą
    public static String line() {
        return "\n" + "-".repeat(40) + "\n";
    }

    // Tworzy kolorowy komunikat
    public static String success(String message) {
        return GREEN + "[OK] " + message + RESET;
    }

    public static String warning(String message) {
        return YELLOW + "[!]" + message + RESET;
    }

    public static String error(String message) {
        return RED + "[ERROR] " + message + RESET;
    }

    // Czyszczenie terminala
    public static void clearScreen() {
        // ANSI (działa w normalnych terminalach)
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Dodatkowo — wypychanie tekstu w IntelliJ
        System.out.println("\n".repeat(30));
    }

    // Pomocnicza metoda do dopełniania spacji
    private static String padAnsiRight(String text, int length) {
        int visibleLength = stripAnsi(text).length();
        int padding = length - visibleLength;
        return text + " ".repeat(Math.max(0, padding));
    }

    private static String stripAnsi(String input) {
        return input.replaceAll("\u001B\\[[;\\d]*m", "");
    }


}