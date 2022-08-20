package net.bodz.bas.codegen;

public class Indentation {

    // public int width;
    public int level;
    public int textOffset;

    public Indentation(int level, int textOffset) {
        this.level = level;
        this.textOffset = textOffset;
    }

    public static Indentation compute(String s) {
        return compute(s, 4, 8);
    }

    public static Indentation compute(String s, int indentSize, int tabSize) {
        int firstNonSpcOffset = 0, len = s.length();
        int width = 0;
        L: while (firstNonSpcOffset < len) {
            switch (s.charAt(firstNonSpcOffset)) {
            case ' ':
                width++;
                break;

            case '\t':
                width += tabSize - (width % tabSize);
                break;

            default:
                break L;
            }
            firstNonSpcOffset++;
        }
        int level = width / indentSize;
        return new Indentation(level, firstNonSpcOffset);
    }

}
