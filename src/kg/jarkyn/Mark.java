package kg.jarkyn;

public enum Mark {
    X, O, NONE;

    public Mark opponent() {
        return this == Mark.X ? Mark.O : Mark.X;
    }

    public static Mark toMark(String markValue) {
        for (Mark mark : Mark.values()) {
            if (markValue.equals(mark.toString())) {
                return mark;
            }
        }
        return Mark.NONE;
    }
}
