import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            loading();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Что нужно делать?:\n\t" +
                    Action.ENCRYPT.ordinal() + " - Зашифровать\n\t" +
                    Action.DECRYPT.ordinal() + " - Расшифровать\n\t" +
                    Action.BRUTEFORCE.ordinal() + " - Подбор пароля\n----> ");
            Action action;
            while (true) {
                try {
                    int actionIndex = Integer.parseInt(scanner.nextLine());
                    action = Action.getAction(actionIndex);
                    if (action != null)
                        break;
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
                System.out.println("Введите число из списка!\n----> ");
            }

            System.out.print("Введите путь к файлу: ");
            String path = scanner.nextLine();

            if (action == Action.TEST) {
                test();
            } else if (action == Action.ENCRYPT || action == Action.DECRYPT) {
                System.out.println("Введите ключ шифрования (целое число): ");
                int key;
                while (true) {
                    try {
                        key = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Введите целое число: ");
                    }
                }
                encryptDecryptAction(path, key, action);
            } else if (action == Action.BRUTEFORCE) {
                    BruteforceManager bruteforceManager = new BruteforceManager();
                    try {
                        bruteforceManager.doBruteForce(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        }
    }

    /**
     * Пафосная загрузка)
     *
     * @throws InterruptedException
     */
    private static void loading() throws InterruptedException {
        System.out.println("EnDeCripter by Sergey Muzhzhukhin.");
        Thread.sleep(500);
        System.out.print("Инициализация");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(700);
        }
        System.out.println();
    }


    private static void encryptDecryptAction(String path, int key, Action action) {
        FileManager fileManager = new FileManager(path);
        try {
            String data = fileManager.getFileData();
            if (action == Action.ENCRYPT) {
                data = Cryptographer.encrypt(data, key);
                fileManager.saveEncriptedFile(data);
            } else if (action == Action.DECRYPT) {
                data = Cryptographer.decrypt(data, key);
                fileManager.saveDecriptedFile(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() {

    }
}
