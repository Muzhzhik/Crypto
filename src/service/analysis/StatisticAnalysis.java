package service.analysis;

import java.util.HashMap;
import java.util.Map;

public class StatisticAnalysis implements Analyser{

    private static final int EMPTY_VALUE = -1;

    public String makeAnalyse(String encryptedData, String notEncryptedData) {
        Map<Character, Integer> encryptedDataAnalysis = getCharactersPercent(encryptedData);
        Map<Character, Integer> nonEncDataAnalysis = getCharactersPercent(notEncryptedData);

        Map<Character, Character> newAlphabet = new HashMap<>();

        for(var encEntry : encryptedDataAnalysis.entrySet()) {
            if (encEntry.getValue() == EMPTY_VALUE)
                continue;

            Map.Entry<Character, Integer> bestСoincidence = null;
            for(var entry : nonEncDataAnalysis.entrySet()) {
                if (entry.getValue() == EMPTY_VALUE)
                    continue;

                if (bestСoincidence == null) {
                    bestСoincidence = entry;
                } else if (entry.getValue() == encEntry.getValue()) {
                    newAlphabet.put(encEntry.getKey(), entry.getKey());
                    bestСoincidence = null;
                    entry.setValue(EMPTY_VALUE);
                    encEntry.setValue(EMPTY_VALUE);
                    break;
                } else {
                    if (Math.abs(bestСoincidence.getValue() - encEntry.getValue()) < Math.abs(bestСoincidence.getValue() - entry.getValue())) {
                        bestСoincidence = entry;
                    }
                }
            }
            if (bestСoincidence != null) {
                newAlphabet.put(encEntry.getKey(), bestСoincidence.getKey());
                nonEncDataAnalysis.replace(bestСoincidence.getKey(), EMPTY_VALUE);
            }
        }

        StringBuilder encryptedString = new StringBuilder();
        for(char c : encryptedData.toUpperCase().toCharArray()) {
            String str = newAlphabet.get(c).toString();
            encryptedString.append(str);
        }

        return encryptedString.toString();
    }

    private Map<Character, Integer> getCharactersPercent(String data) {
        Map<Character, Integer> result = new HashMap<>();
        char[] chars = data.toUpperCase().toCharArray();
        for (Character c : chars) {
            if (result.containsKey(c)) {
                result.put(c, result.get(c) + 1);
                continue;
            }
            result.put(c, 1);
        }

        for(var entry : result.entrySet()) {
            int percent = (int) Math.round((entry.getValue() * 100.0) / chars.length);
            entry.setValue(percent);
        }

        return result;
    }

    //C:\JavaRushFiles\27_03_2022_14-41-13.txt

    //C:\JavaRushFiles\test.txt

}
