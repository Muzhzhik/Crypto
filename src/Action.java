public enum Action {
    TEST, ENCRYPT, DECRYPT, BRUTEFORCE, ANALYZE;

    public static Action getAction(int actionIndex) {
        Action action = null;
        if (actionIndex >= Action.TEST.ordinal() && actionIndex <= Action.ANALYZE.ordinal()) {
            if (actionIndex == Action.TEST.ordinal())
                action = Action.TEST;
            else if (actionIndex == Action.ENCRYPT.ordinal())
                action = Action.ENCRYPT;
            else if (actionIndex == Action.DECRYPT.ordinal())
                action = Action.DECRYPT;
            else
                action = Action.ANALYZE;
        }
        return action;
    }
}
