import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       //test();
        try {
            loading();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Введите путь и имя файла: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.print("Что нужно делать?:\n\t" + Actions.ENCRYPT.ordinal() + " - Зашифровать\n\t" + Actions.DECRYPT.ordinal() + " - Расшифровать\n\t" + Actions.BRUTEFORCE.ordinal() + " - Подбор пароля\n----> ");
        int action = scanner.nextInt();
    }

    /**
     * Пафосная загрузка)
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

    // Тесты
    private static void test() {
        String path = "C:\\JavaRushFiles\\inputFile_enc.txt";
        FileManager fileManager = new FileManager(path);
        try {
            String data = fileManager.getFileData();
            data = Cryptographer.decrypt(data, 23);
            fileManager.saveEncriptedFile(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encriptString = "Привет ребята!";
        int key = -2;
        String result = Cryptographer.encrypt(encriptString, key);
        System.out.println(result);
        System.out.println(Cryptographer.decrypt(result, key));
    }
}
