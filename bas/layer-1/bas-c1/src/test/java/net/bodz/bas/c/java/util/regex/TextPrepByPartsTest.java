package net.bodz.bas.c.java.util.regex;

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
            protected String matched(String part) {
                int num = Integer.parseInt(part);
                return String.valueOf(num + 1);
            }

            @Override
            protected String unmatched(String part) {
                return part;
            }

        };

        String[] lines = new String[] { "hello 123, 456 world!", "22-12, and oh 666", "nothing!", };

        for (String line : lines) {
            String out = pp.process(line);
            System.out.println(out);
        }

    }
}
