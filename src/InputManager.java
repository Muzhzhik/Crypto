import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    public String createInput(String message) {
        String string;
        if (message != null && !message.equals("")) {
            Logger.printToConsole(message);
        }
        string = scanner.nextLine();
        return string;
    }

    public void close() {
        scanner.close();
    }
}
