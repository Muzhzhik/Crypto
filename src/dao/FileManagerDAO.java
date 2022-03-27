package dao;

import controller.ConsoleController;
import utils.ConsoleColors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManagerDAO implements DataDAO{

    @Override
    public String getData(String sourcePath) {
        String result = null;
        Path path = Path.of(sourcePath);
        if (Files.isRegularFile(path)) {
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                ConsoleController.printColorText("Error: Cant read file\n", ConsoleColors.RED_BOLD_BRIGHT);
            }
            result = new String(bytes);
        } else {
            ConsoleController.printColorText("Error: Cant find file\n", ConsoleColors.RED_BOLD_BRIGHT);
        }
        return result;
    }

    @Override
    public void writeData(String sourcePath, String data) {
        Path path = Path.of(sourcePath);

        // Добавляем дату, чтобы использовать ее в имени файла
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH-mm-ss");
        String date = dateTime.format(dateTimeFormatter);

        Path newPath = path.getParent();
        String fileName = path.getFileName().toString().split("\\.")[0];
        fileName = fileName + "_" + date + ".";
        fileName = fileName + path.getFileName().toString().split("\\.")[1];
        Path newFileName = Path.of(fileName);
        newPath = newPath.resolve(newFileName);
        try {
            Files.write(newPath, data.getBytes());
        } catch (IOException e) {
            ConsoleController.printColorText("Error: Cant write file", ConsoleColors.RED_BOLD_BRIGHT);
        }
        ConsoleController.printColorText("File created " + newPath, ConsoleColors.GREEN_BOLD_BRIGHT);
    }
}
