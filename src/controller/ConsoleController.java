package controller;

import service.ActionManager;
import utils.Action;
import utils.ConsoleColors;

import java.util.Scanner;

public class ConsoleController {
    private static final String APP_NAME = "************ CRYPTOR ************";

    public void printMainMenu() {
        printHeader();
        printMakeChoise();
    }

    private void printHeader() {
        printColorText("dev by Sergey Muzhzhukhin.\n\n", ConsoleColors.WHITE);
        printColorText(APP_NAME, ConsoleColors.BLUE_BOLD);
        printColorText("\n\n", ConsoleColors.RESET);
    }

    private void printMakeChoise() {
        Action action;
        Scanner scanner = new Scanner(System.in);
        printColorText("Make your choise:\n", ConsoleColors.RESET);
        printColorText(Action.ENCRYPT.ordinal() + " - Encrypt\n", ConsoleColors.BLUE);
        printColorText(Action.DECRYPT.ordinal() + " - Decrypt\n", ConsoleColors.BLUE);
        printColorText(Action.BRUTEFORCE.ordinal() + " - Brut force\n\n", ConsoleColors.BLUE);
        printColorText(Action.EXIT.ordinal() + " - Exit\n\n", ConsoleColors.CYAN);
        do {
            printColorText("> ", ConsoleColors.RESET);
            String read = scanner.nextLine();
            try {
                int actionIndex = Integer.parseInt(read.trim());
                action = Action.getAction(actionIndex);
                if (action != null) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                // do nothing
            }
            printColorText("Make right choise\n", ConsoleColors.RED);
        } while (true);

        new ActionManager(action).doAction();
    }


    public static void printColorText(String text, String color) {
        System.out.print(color + text);
        System.out.print(ConsoleColors.RESET);
    }
}
