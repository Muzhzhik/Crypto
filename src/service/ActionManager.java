package service;

import controller.ConsoleController;
import dao.FileManagerDAO;
import service.brutforce.BrutForce;
import service.brutforce.CaesarBrutForce;
import service.cryptor.CaesarCryptor;
import service.cryptor.Cryptor;
import service.analysis.Analyser;
import service.analysis.StatisticAnalysis;
import service.logger.Logger;
import utils.Action;
import utils.ConsoleColors;

import java.util.Scanner;

public class ActionManager {

    private final Action action;
    Logger logger = new Logger();

    public ActionManager(Action action) {
        this.action = action;
    }

    public void doAction() {
        if (action == Action.EXIT)
            exitOrResumeQuestion();

        Scanner scanner = new Scanner(System.in);

        if (action == Action.ANALYZE) {
            logger.info("\nEnter encrypted file path: ", ConsoleColors.RESET);
        } else {
            logger.info("\nEnter path to file: ", ConsoleColors.RESET);
        }
        String path = scanner.nextLine();
        FileManagerDAO fileManager = new FileManagerDAO();
        String data = fileManager.getData(path);

        checkDataNullEmpty(data);

        Cryptor cryptor = new CaesarCryptor();
        if (action == Action.ENCRYPT || action == Action.DECRYPT) {
            doEncryptDecryptAction(scanner, path, fileManager, data, cryptor);

        } else if (action == Action.BRUTEFORCE) {
            BrutForce brutforce = new CaesarBrutForce();
            String brutforceResult = brutforce.doBrutForce(data);
            if (brutforce != null) {
                saveResultToFileYN(scanner, path, fileManager, brutforceResult);
            }
        } else if (action == Action.ANALYZE) {
            logger.info("WARNING! Then bigger text then more correct result!\n", ConsoleColors.YELLOW_UNDERLINED);
            logger.info("\nEnter filename for analyse: ", ConsoleColors.RESET);
            String analyseFilePath = scanner.nextLine();
            String analyseData = fileManager.getData(analyseFilePath);
            checkDataNullEmpty(analyseData);


            Analyser analyser = new StatisticAnalysis();
            String result = analyser.makeAnalyse(data, analyseData);
            logger.info("\nAnalyse complete.", ConsoleColors.RESET);
            saveResultToFileYN(scanner, path, fileManager, result);
        }
        exitOrResumeQuestion();
    }

    private void doEncryptDecryptAction(Scanner scanner, String path, FileManagerDAO fileManager, String data, Cryptor cryptor) {
        int key;
        do {
            try {
                logger.info("Enter key value: ", ConsoleColors.RESET);
                key = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                logger.error("Error: Enter integer number\n");
            }
        } while (true);


        String newData = switch (action) {
            case ENCRYPT -> cryptor.encrypt(data, key);
            case DECRYPT -> cryptor.decrypt(data, key);
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };

        fileManager.writeData(path, newData);
    }

    private void checkDataNullEmpty(String data) {
        if (data == null) {
            exitOrResumeQuestion();
        } else if (data.equals("")) {
            logger.error("Data is empty.");
            exitOrResumeQuestion();
        }
    }

    private void saveResultToFileYN(Scanner scanner, String path, FileManagerDAO fileManager, String brutforceResult) {
        do {
            logger.info("\nSave result to file?: Y/N > ", ConsoleColors.YELLOW);
            String yn = scanner.nextLine();
            if (yn.trim().equalsIgnoreCase("y")) {
                fileManager.writeData(path, brutforceResult);
                break;
            } else if (yn.trim().equalsIgnoreCase("n")) {
                break;
            }
        } while (true);
    }

    private void exitOrResumeQuestion() {
        Scanner scanner = new Scanner(System.in);
        do {
            logger.info("\nWant to resume? Y/N > ", ConsoleColors.RESET);
            String yn = scanner.nextLine();
            if (yn.trim().equalsIgnoreCase("y")) {
                new ConsoleController().printMainMenu();
                break;
            } else if (yn.trim().equalsIgnoreCase("n")) {
                System.exit(0);
                break;
            }
        } while (true);
    }
}
