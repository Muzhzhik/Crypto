package service;

import controller.ConsoleController;
import dao.FileManagerDAO;
import service.brutforce.Brutforce;
import service.brutforce.CaesarBrutfoce;
import service.cryptor.CaesarCryptor;
import service.cryptor.Cryptor;
import utils.Action;
import utils.ConsoleColors;

import java.util.Scanner;

public class ActionManager {

    private final Action action;

    public ActionManager(Action action) {
        this.action = action;
    }

    public void doAction() {
        if (action == Action.EXIT)
            exitFromApp();

        Scanner scanner = new Scanner(System.in);
        ConsoleController.printColorText("Enter path to file: ", ConsoleColors.RESET);
        String path = scanner.nextLine();
        FileManagerDAO fileManager = new FileManagerDAO();
        String data = fileManager.getData(path);

        if (data == null)
            exitFromApp();

        Cryptor cryptor = new CaesarCryptor();
        if (action == Action.ENCRYPT || action == Action.DECRYPT) {
            int key;
            do {
                try {
                    ConsoleController.printColorText("Enter key value: ", ConsoleColors.RESET);
                    key = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    ConsoleController.printColorText("Error: Enter integer number\n", ConsoleColors.RED_BOLD_BRIGHT);
                }
            } while (true);


            String newData = switch (action) {
                case ENCRYPT -> cryptor.encrypt(data, key);
                case DECRYPT -> cryptor.decrypt(data, key);
                default -> throw new IllegalStateException("Unexpected value: " + action);
            };

            fileManager.writeData(path, newData);

        } else if (action == Action.BRUTEFORCE) {
            Brutforce brutforce = new CaesarBrutfoce();
            String brutforceResult = brutforce.doBrutforce(data);

        }
    }

    private void exitFromApp() {
        System.exit(0);
    }
}
