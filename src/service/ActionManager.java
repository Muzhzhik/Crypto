package service;

import dao.FileManagerDAO;
import service.brutforce.Brutforce;
import service.brutforce.CaesarBrutfoce;
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
            exitFromApp();

        Scanner scanner = new Scanner(System.in);
        logger.info("\nEnter path to file: ", ConsoleColors.RESET);
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

        } else if (action == Action.BRUTEFORCE) {
            Brutforce brutforce = new CaesarBrutfoce();
            String brutforceResult = brutforce.doBrutforce(data);
            if (brutforce != null) {
                saveResultToFileYN(scanner, path, fileManager, brutforceResult);
            }
        } else if (action == Action.ANALYZE) {
            logger.info("\nEnter filename for analyse: ", ConsoleColors.RESET);
            String analyseFilePath =  scanner.nextLine();
            String analyseData = fileManager.getData(analyseFilePath);
            if (analyseData == null)
                exitFromApp();

            Analyser analyser = new StatisticAnalysis();
            String result = analyser.makeAnalyse(data, analyseData);
            saveResultToFileYN(scanner, path, fileManager, result);
        }
    }

    private void saveResultToFileYN(Scanner scanner, String path, FileManagerDAO fileManager, String brutforceResult) {
        do {
            logger.info("\nSave result to file?: Y/N > ", ConsoleColors.YELLOW);
            String yn = scanner.nextLine();
            if (yn.trim().equalsIgnoreCase("y")) {
                fileManager.writeData(path, brutforceResult);
                break;
            } else if (yn.trim().equalsIgnoreCase("n")){
                break;
            }
        } while (true);
    }

    private void exitFromApp() {
        System.exit(0);
    }
}
