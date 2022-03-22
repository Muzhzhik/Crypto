public enum Action {
    TEST, ENCRYPT, DECRYPT, BRUTEFORCE, ANALYZE;

    public static Action getAction(int actionIndex) {
        Action action = null;
        if (actionIndex >= TEST.ordinal() && actionIndex <= ANALYZE.ordinal()) {
            action = values()[actionIndex];
        }
        return action;
    }
}
