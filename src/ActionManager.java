import java.io.IOException;

public class ActionManager {

    public static void doAction(Action action) {
        InputManager inputManager = new InputManager();
        String path =  inputManager.createInput("Введите путь к файлу: ");
        if (action == Action.TEST) {
            test();
        } else if (action == Action.ENCRYPT || action == Action.DECRYPT) {
            int key;
            while (true) {
                try {
                    key = Integer.parseInt(inputManager.createInput("Введите ключ шифрования (целое число): "));
                    break;
                } catch (IllegalArgumentException e) {
                   inputManager.printToConsole("Неверный ввод.");
                }
            }
            encryptDecryptAction(path, key, action);
        } else if (action == Action.BRUTEFORCE) {
            Bruteforce bruteforceManager = new Bruteforce();
            try {
                bruteforceManager.doBruteForce(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
