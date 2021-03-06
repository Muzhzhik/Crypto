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

            findBestCoincidence(nonEncDataAnalysis, newAlphabet, encEntry);
        }

        StringBuilder encryptedString = new StringBuilder();
        for(char c : encryptedData.toUpperCase().toCharArray()) {
            String str = newAlphabet.get(c).toString();
            encryptedString.append(str);
        }

        return encryptedString.toString();
    }

    private void findBestCoincidence(Map<Character, Integer> nonEncDataAnalysis, Map<Character, Character> newAlphabet, Map.Entry<Character, Integer> encEntry) {
        Map.Entry<Character, Integer> bestCoincidence = null;
        for(var entry : nonEncDataAnalysis.entrySet()) {
            if (entry.getValue() == EMPTY_VALUE)
                continue;

            if (bestCoincidence == null) {
                bestCoincidence = entry;
            } else if (entry.getValue().equals(encEntry.getValue())) {
                newAlphabet.put(encEntry.getKey(), entry.getKey());
                bestCoincidence = null;
                entry.setValue(EMPTY_VALUE);
                encEntry.setValue(EMPTY_VALUE);
                break;
            } else {
                if (Math.abs(bestCoincidence.getValue() - encEntry.getValue()) < Math.abs(bestCoincidence.getValue() - entry.getValue())) {
                    bestCoincidence = entry;
                }
            }
        }

        if (bestCoincidence != null) {
            newAlphabet.put(encEntry.getKey(), bestCoincidence.getKey());
            nonEncDataAnalysis.replace(bestCoincidence.getKey(), EMPTY_VALUE);
        }
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
}
