package utils;

public enum Action {
    EXIT, ENCRYPT, DECRYPT, BRUTEFORCE, ANALYZE;

    public static Action getAction(int actionIndex) {
        Action action = null;
        if (actionIndex >= EXIT.ordinal() && actionIndex <= ANALYZE.ordinal()) {
            action = values()[actionIndex];
        }
        return action;
    }
}
