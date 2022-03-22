import java.util.*;

public class Alphabet {

    private static final List<Character> characterList = new ArrayList<>();

    private static String ALPHABET;
    private static Map<Character, Integer> MAP_ALPHABET;
    private static char[] CHAR_ALPHABET;

    static {
        initAlphabet();
    }

    private static void initAlphabet() {
        MAP_ALPHABET = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        int charIndexCounter = 0;
        // Add Characters
        for (int i = 'А'; i <= 'Я'; i++) {
            stringBuilder.append((char) i);
            MAP_ALPHABET.put((char) i, charIndexCounter++);
            characterList.add((char) i); // test
        }

        // Add other symbols
        char[] chars = {'.', ',', ':', '-', '!', '?', ' '};
        for (Character symbol : chars) {
            stringBuilder.append(symbol);
            MAP_ALPHABET.put(symbol, charIndexCounter++);
            characterList.add(symbol); // test
        }
        ALPHABET = stringBuilder.toString();
        CHAR_ALPHABET = ALPHABET.toCharArray();

//        System.out.println("Current Alphabet: " + ALPHABET + "\nAlphabet max index: " + (ALPHABET.length() - 1));
    }

    public static String getAlphabet() {
        return ALPHABET;
    }

    public static char[] getCharAlphabet() {
        return CHAR_ALPHABET;
    }

    public static char getChar(int index) {
        return CHAR_ALPHABET[index];
    }

    public static char getChar(char currentChar, int offset) {
        int realOffset = -getCharIndex(currentChar)-offset;
        Collections.rotate(characterList, -getCharIndex(currentChar)-offset);
        char result = characterList.get(0);
        Collections.rotate(characterList, -realOffset);
        return result;

      /*  currentChar = charToUpperCase(currentChar);
        char result;
        int charMaxIndex = CHAR_ALPHABET.length - 1;
        int currentCharIndex = getCharIndex(currentChar);

        if (Math.abs(offset) > CHAR_ALPHABET.length) {
            int remains = Math.abs(offset % CHAR_ALPHABET.length);
            if (remains == 0) {
                offset = remains;
            } else if (remains > 0 && remains < CHAR_ALPHABET.length) {
                offset = offset > 0 ? remains : -remains;
            }
        }

        if (offset == 0) {
            result = getChar(currentCharIndex);
        } else if (currentCharIndex + offset > charMaxIndex) {
            int newIndex = currentCharIndex + offset - charMaxIndex - 1;
            result = getChar(newIndex);
        } else if (currentCharIndex + offset < 0) {
            int newIndex = charMaxIndex - Math.abs(currentCharIndex + offset) + 1;
            result = getChar(newIndex);
        } else {
            result = getChar(currentCharIndex + offset);
        }
        return result; */
    }

    public static int getCharIndex(char c) {
        c = charToUpperCase(c);
        Integer result = MAP_ALPHABET.get(c);
        if (result == null)
            result = -1;
        return result;
    }

    private static char charToUpperCase(char currentChar) {
        String upperCaseChar = "" + currentChar;
        upperCaseChar = upperCaseChar.toUpperCase();
        currentChar = upperCaseChar.charAt(0);
        return currentChar;
    }
}
