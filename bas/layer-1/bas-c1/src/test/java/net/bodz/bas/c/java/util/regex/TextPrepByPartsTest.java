package net.bodz.bas.c.java.util.regex;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TextPrepByPartsTest
        extends Assert {

    @Test
    public void testAdd() {
        Pattern pattern = Pattern.compile("\\d+");
        TextPrepByParts pp = new TextPrepByParts(pattern) {
            @Override
            protected void matched(CharSequence in, int start, int end, Appendable out)
                    throws IOException {
                String part = in.subSequence(start, end).toString();
                int num = Integer.parseInt(part);
                out.append(String.valueOf(num + 1));
            }

            @Override
            protected void unmatched(CharSequence in, int start, int end, Appendable out)
                    throws IOException {
                out.append(in, start, end);
            }
        };

        String[] lines = new String[] { "hello 123, 456 world!", "22-12, and oh 666", "nothing!", };

        for (String line : lines) {
            String out = pp.process(line);
            System.out.println(out);
        }

    }
}
