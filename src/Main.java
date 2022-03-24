import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        try {
            loading();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        InputManager inputManager = new InputManager();
        Action action;
        while (true) {
            String read = inputManager.createInput("Что нужно делать?:\n\t" +
                    Action.ENCRYPT.ordinal() + " - Зашифровать\n\t" +
                    Action.DECRYPT.ordinal() + " - Расшифровать\n\t" +
                    Action.BRUTEFORCE.ordinal() + " - Подбор пароля\n----> ");
            try {
                int actionIndex = Integer.parseInt(read);
                action = Action.getAction(actionIndex);
                if (action != null)
                    break;
            } catch (IllegalArgumentException e) {
                // do nothing
            }
            inputManager.printToConsole("Введите число из списка!\n----> ");
        }

        ActionManager.doAction(action);
        inputManager.close();
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
            Thread.sleep(500);
        }
        System.out.println();
    }
}
