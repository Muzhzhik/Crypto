package controller;

import service.ActionManager;
import service.logger.Logger;
import utils.Action;
import utils.ConsoleColors;

import java.util.Scanner;

public class ConsoleController {
    private static final String APP_NAME = "************ CRYPTOR ************";
    Logger logger = new Logger();

    public void printMainMenu() {
        printHeader();
        printMakeChoice();
    }

    private void printHeader() {
        logger.info("\n", ConsoleColors.WHITE);
        logger.info(APP_NAME, ConsoleColors.BLUE_BOLD);
        logger.info("\n\n", ConsoleColors.RESET);
    }

    private void printMakeChoice() {
        Action action;
        Scanner scanner = new Scanner(System.in);
        logger.info("Make your choice:\n", ConsoleColors.RESET);
        logger.info(Action.ENCRYPT.ordinal() + " - Encrypt\n", ConsoleColors.BLUE);
        logger.info(Action.DECRYPT.ordinal() + " - Decrypt\n", ConsoleColors.BLUE);
        logger.info(Action.BRUTEFORCE.ordinal() + " - Brut force\n", ConsoleColors.BLUE);
        logger.info(Action.ANALYZE.ordinal() + " - Crypto analysis\n\n", ConsoleColors.BLUE);
        logger.info(Action.EXIT.ordinal() + " - Exit\n\n", ConsoleColors.CYAN);
        do {
            logger.info("> ", ConsoleColors.RESET);
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
            logger.info("Make right choice\n", ConsoleColors.RED);
        } while (true);

        new ActionManager(action).doAction();
    }
}
