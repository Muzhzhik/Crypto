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
     * Сохраняет данные в файл. Создает новый с тем же именем, но с приставкой .._enc
     * @param data
     * @throws IOException
     */
    public void saveEncriptedFile(String data) throws IOException {
        Path newPath = path.getParent();
        Path newFileName = Path.of(path.getFileName().toString().split("\\.")[0] + "_enc." + path.getFileName().toString().split("\\.")[1]);
        newPath = newPath.resolve(newFileName);
        Files.write(newPath, data.getBytes());
    }

}
