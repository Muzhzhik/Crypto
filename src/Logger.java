public interface Logger {
    default void log(FileManager fileManager, String message) {
        // Записываем лог в файл
    }

    default void printToConsole(String message) {
        System.out.print(message);
    }
}
