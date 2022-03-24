import java.util.Scanner;

public class InputManager implements Logger {
    private static final Scanner scanner = new Scanner(System.in);

    public String createInput(String message) {
        String string;
        if (message != null && !message.equals("")) {
            printToConsole(message);
        }
        string = scanner.nextLine();
        return string;
    }

    public void close() {
        scanner.close();
    }
}
