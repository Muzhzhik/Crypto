public class Сryptographer {

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

    public static String decrypt(String data, int key) {
        return null;
    }

}
