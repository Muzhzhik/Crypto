import java.util.*;

public class Alphabet {

    private static final Character FROM_CHAR = 'А';
    private static final Character TO_CHAR = 'Я';
    private static final char[] ADDITIONAL_CHARS =  {'.', ',', ':', '-', '!', '?', ' '};


    private static final List<Character> CHARACTER_LIST = new ArrayList<>();
    private static final Map<Character, Integer> MAP_ALPHABET = new HashMap<>();

    static {
        initAlphabet();
    }

    /**
     * Инициализация алфавита
     */
    private static void initAlphabet() {
        int charIndexCounter = 0;
        // Add Characters
        for (int i = FROM_CHAR; i <= TO_CHAR; i++) {
            MAP_ALPHABET.put((char) i, charIndexCounter++);
            CHARACTER_LIST.add((char) i);
        }

        // Add other symbols
        for (Character symbol : ADDITIONAL_CHARS) {
            MAP_ALPHABET.put(symbol, charIndexCounter++);
            CHARACTER_LIST.add(symbol);
        }
    }

    /**
     * Получить символ по индексу
     * @param index индекс символа
     * @return символ в массиве
     */
    public static char getChar(int index) {
        return CHARACTER_LIST.get(index);
    }

    /**
     * Принимает символ, смещается относительно него на offset и возвращает новый
     * @param currentChar символ относительно которого смещаемся
     * @param offset на сколько символов сместиться
     * @return новый символ
     */
    public static char getChar(char currentChar, int offset) {
        int realOffset = -getCharIndex(currentChar)-offset;
        Collections.rotate(CHARACTER_LIST, -getCharIndex(currentChar)-offset);
        char result = CHARACTER_LIST.get(0);
        Collections.rotate(CHARACTER_LIST, -realOffset);
        return result;
    }

    /**
     * Получает индекс символа
     * @param c символ
     * @return индекс
     */
    public static int getCharIndex(char c) {
        c = charToUpperCase(c);
        Integer result = MAP_ALPHABET.get(c);
        if (result == null)
            result = -1;
        return result;
    }

    /**
     * Приводит символ в верхний регистр
     * @param currentChar символ
     * @return символ в верхнем регистре
     */
    private static char charToUpperCase(char currentChar) {
        String upperCaseChar = "" + currentChar;
        upperCaseChar = upperCaseChar.toUpperCase();
        currentChar = upperCaseChar.charAt(0);
        return currentChar;
    }
}
