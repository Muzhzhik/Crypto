package dao;

public interface DataDAO {
    String getData(String sourcePath);
    void writeData(String sourcePath, String data);
}
