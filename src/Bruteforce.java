import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bruteforce {

    public void doBruteForce(String path) throws IOException {
        Map<Integer, String> searchResults = new HashMap<>();
        FileManager fileManager = new FileManager(path);
        String data = fileManager.getFileData();
        if (data != null && data.length() > 0) {
            int key = 0;
            boolean stop = false;
            while (!stop) {
                // Понимаем, что прошли круг и останавливаемся
                if (key != 0 && key - Alphabet.getCharacterList().size() == 0) {
                    stop = true;
                }

                String encData = Cryptographer.decrypt(data, key);
                if (isBrutForceDone(encData)) {
                    // Записываем варианты которые нашли в мапу
                    searchResults.put(key, data);
                }
                if (!stop)
                    key++;
            }
            System.out.println("Подбор закончен. Вариантов: " + searchResults.size());
        } else {
            System.out.println("В файле нет данных.");
        }
    }

    //C:\JavaRushFiles\test_enc.txt

    private boolean isBrutForceDone(String dataForAnalyze) {
        String data = dataForAnalyze.toUpperCase();
        // Над этим условием можно подумать еще конечно
        if (data.endsWith(":") || data.endsWith(",") || data.endsWith("-"))
            return false;

        String[] splitString = data.split("\\s");
        int countOfRealWords = 0;

        for (String s : splitString) {
            if (!s.equals("")) {
                String additionalCh = Alphabet.containsAdditional(s);
                if (additionalCh != null) {
                    // Если сходу понимаем, что чушь, сразу выкидываем
                    if (!Alphabet.startWithAdditional(s) && Alphabet.containsAdditionalCount(s) > 2 && !s.equals("-")) {
                        return false;
                    } else if (Alphabet.startWithAdditional(s) && countOfRealWords <=0) {
                        return false;
                    }

                    if (!s.startsWith(additionalCh)) {
                        if (s.endsWith(additionalCh)) {
                            String[] lastSplit = s.split("\\" + additionalCh);
                            if (lastSplit.length == 1 && Alphabet.containsAdditional(lastSplit[0]) == null) {
                                countOfRealWords++;
                            } else {
                                countOfRealWords--;
                            }
                        } else {
                            if (Alphabet.containsAdditionalCount(s) == 1 && s.contains("-")){
                               countOfRealWords++;
                            } else {
                                countOfRealWords--;
                            }
                        }
                    } else {
                        countOfRealWords--;
                    }
                } else {
                    countOfRealWords++;
                }
            }
        }
        return countOfRealWords > 0;
    }
}
