package net.bodz.bas.ui.css3.property;

public enum BackgroundRepeatMode {

    /** The image is repeated both horizontally and vertically. */
    repeat("repeat"),

    /** The image is repeated horizontally only. */
    repeat_x("repeat-x"),

    /** The image is repeated vertically only. */
    repeat_y("repeat-y"),

    /** The image is not repeated: only one copy of the image is drawn. */
    no_repeat("no-repeat"),

    ;

    private final String s;

    private BackgroundRepeatMode(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

}
