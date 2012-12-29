package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;

public class WordsTagType
        extends ScalarTagType<String[]> {

    @Override
    protected String[] parse(String s)
            throws ParseException {
        String[] words = s.split("\\s+");
        return words;
    }

    @Override
    protected String format(String[] array)
            throws FormatException {
        StringBuilder sb = new StringBuilder(array.length * 32);
        for (int i = 0; i < array.length; i++) {
            if (i != 0)
                sb.append(' ');
            sb.append(array[i]);
        }
        return sb.toString();
    }

    static final WordsTagType instance = new WordsTagType();

    public static WordsTagType getInstance() {
        return instance;
    }

}
