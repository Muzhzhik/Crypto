import java.io.IOException;

public class BruteforceManager {

    public void doBruteForce(String path) throws IOException {
        FileManager fileManager = new FileManager(path);
        String data = fileManager.getFileData();
        if (data != null && data.length() > 0) {
            int key = 0;
            boolean stop = false;
            while (!stop) {
               stop = isBrutForceDone(Cryptographer.decrypt(data, key));
               if (!stop)
                   key++;
            }
            System.out.println("Подбор закончен. Ключ - " + key);
        } else {
            System.out.println("В файле нет данных.");
        }
    }

    private boolean isBrutForceDone(String dataForAnalyze) {
        String data = dataForAnalyze.toUpperCase();
        String[] split =  data.split(" ");
        int countOfCont = 0;
        for (String s : split) {
            for (String ch : Alphabet.getAdditionalChars()) {
                if (s.endsWith(ch) && !s.startsWith(ch) && !s.contains(ch)) { // Кривое условие. Надо бы как-то regex подтянуть!
                    countOfCont++;
                }
            }
        }
        return countOfCont > 0;
    }
}
