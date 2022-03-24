public class Main {

    public static void main(String[] args) {
        try {
            loading();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            Logger.printToConsole("Введите число из списка!\n----> ");
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
        Logger.printToConsole("EnDeCripter by Sergey Muzhzhukhin.\n");
        Thread.sleep(500);
        Logger.printToConsole("Инициализация");
        for (int i = 0; i < 3; i++) {
            Logger.printToConsole(".");
            Thread.sleep(500);
        }
        Logger.printToConsole("\n");
    }
}
