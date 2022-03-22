public class Cryptographer {

    /**
     * Зашифровывает строку по ключу
     * @param data строка для шифрования
     * @param key ключ
     * @return зашифрованую строку
     */
    public static String encrypt(String data, int key) {
        String result = null;
        if (data != null && !data.equals("")) {
            StringBuilder encryptedStr = new StringBuilder();
            for(char c : data.toCharArray()) {
                encryptedStr.append(Alphabet.getChar(c, key));
            }
            result = encryptedStr.toString();
        }
        return result;
    }

    /**
     * Расшифровывает строку по ключу
     * @param data зашифрованая строка
     * @param key ключ
     * @return расшифрованая строка
     */
    public static String decrypt(String data, int key) {
        return encrypt(data, -key);
    }

}
