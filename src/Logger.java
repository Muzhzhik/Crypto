public interface Logger {
    default void log(FileManager fileManager, String message) {

    }

    default void printToConsole(String message) {
        System.out.println(message);
    }
}
