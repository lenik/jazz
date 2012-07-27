package net.bodz.bas.srt.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.srt.SerializeException;

/** s?; (; -> \;) */
public class StringSerializer
        extends AbstractSimpleSerializer {

    @Override
    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        String str = String.valueOf(o);
        s.write(str.length() + ":");
        s.write("\"" + str + "\";");
    }

    static String repeat(String pat, int count) {
        StringBuilder buffer = new StringBuilder(pat.length() * count);
        while (count-- > 0)
            buffer.append(pat);
        return buffer.toString();
    }

    @Override
    public Object unserialize(Reader s)
            throws IOException, SerializeException {
        String slen = readTill(s, ':');
        int len = Integer.parseInt(slen);
        String text = readTill(s, ';');
        // assert text.charAt(pos + 1) == '\"';
        // assert text.charAt(text.length() - 1) == '\"';
        text = text.substring(1, text.length() - 1);
        if (text.length() < len)
            return s + repeat(" ", len - text.length());
        return text.substring(0, len);
    }

}
