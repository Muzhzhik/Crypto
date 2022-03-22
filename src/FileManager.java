import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    private final Path path;

    public FileManager(String path) {
        this.path = Path.of(path);
    }

    /**
     * Загружает файл и читает из него данные
     * @return прочитаные строки
     * @throws IOException
     */
    public String getFileData() throws IOException {
        StringBuilder result = new StringBuilder();
        if (Files.isRegularFile(path)) {
            for (String s : Files.readAllLines(path)) {
                result.append(s);
            }
        } else {
            throw new FileNotFoundException();
        }
        return result.toString();
    }

    /**
     * Сохраняет данные в файл.
     * @param data
     * @throws IOException
     */
    public void saveEncriptedFile(String data) throws IOException {
        saveFile(data, Action.ENCRYPT);
    }

    /**
     * Сохраняет данные в файл.
     * @throws IOException
     */
    public void saveDecriptedFile(String data) throws IOException {
        saveFile(data, Action.DECRYPT);
    }

    private void saveFile(String data, Action action) throws IOException {
        Path newPath = path.getParent();
        String fileName = path.getFileName().toString().split("\\.")[0];
        fileName = fileName + (action == Action.ENCRYPT ? "_enc." : "_dec.");
        fileName = fileName + path.getFileName().toString().split("\\.")[1];
        Path newFileName = Path.of(fileName);
        newPath = newPath.resolve(newFileName);
        Files.write(newPath, data.getBytes());
    }

    /**
     * Проверяет существует ли файл
     * @return да/нет
     */
    public boolean isExistFile() {
        return Files.exists(path);
    }

}
