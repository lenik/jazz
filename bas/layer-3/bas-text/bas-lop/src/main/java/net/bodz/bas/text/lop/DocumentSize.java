package net.bodz.bas.text.lop;

public class DocumentSize {

    public final int length;
    public final int lines;
    public final int lineOffset;

    public DocumentSize(int length, int lines, int lineOffset) {
        this.length = length;
        this.lines = lines;
        this.lineOffset = lineOffset;
    }

    public DocumentSize add(String s) {
        return add(DocumentSize.get(s));
    }

    public DocumentSize add(DocumentSize ds) {
        return add(ds.length, ds.lines, ds.lineOffset);
    }

    public DocumentSize add(int length, int lines, int lineOffset) {
        int mlen = this.length + length;
        int mlines = this.lines + lines;
        int moff = lines == 0 ? this.lineOffset + lineOffset : lineOffset;
        return new DocumentSize(mlen, mlines, moff);
    }

    @Override
    public String toString() {
        return length + "(" + lines + "L+" + lineOffset + ")";
    }

    public static DocumentSize get(String s) {
        int len = s.length();
        int lines = 0;
        int lineOffset = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\r': // ignore
                continue;
            case '\n':
                lines++;
                lineOffset = 0;
                continue;
            default:
                lineOffset++;
            }
        }
        return new DocumentSize(len, lines, lineOffset);
    }

}
