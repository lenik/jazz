package net.bodz.bas.text.util;

import net.bodz.bas.text.util.PatternProcessor;

import org.junit.Test;

public class PatternProcessorTest {

    @Test
    public void testAdd() {

        PatternProcessor pp = new PatternProcessor("\\d+") { //$NON-NLS-1$

            @Override
            protected void matched(String part) {
                int num = Integer.parseInt(part);
                print(num + 1);
            }

            @Override
            protected void unmatched(String part) {
                super.unmatched(part);
            }

        };

        String[] lines = new String[] { "hello 123, 456 world!", //$NON-NLS-1$
                "22-12, and oh 666", "nothing!", }; //$NON-NLS-1$ //$NON-NLS-2$

        for (String line : lines) {
            String out = pp.process(line);
            System.out.println(out);
        }

    }
}
