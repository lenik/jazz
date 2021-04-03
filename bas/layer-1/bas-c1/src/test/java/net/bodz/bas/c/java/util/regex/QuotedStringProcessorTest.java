package net.bodz.bas.c.java.util.regex;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class QuotedStringProcessorTest
        extends Assert {

    @Test
    public void testProcess() {
        final QuoteFormat qf = QuoteFormat.Q;
        final QuotedStringProcessor q = new QuotedStringProcessor(qf) {
            @Override
            protected void matched(CharSequence in, int start, int end, Appendable out)
                    throws IOException {
                out.append("<");
                processQuotedText(in, start, end, out);
                out.append(">");
            }

            @Override
            protected void unmatched(CharSequence in, int start, int end, Appendable out)
                    throws IOException {
                out.append("[");
                out.append(in, start, end);
                out.append("]");
            }
        };
        class D {
            void o(String input, String expected) {
                String actual = q.process(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); // 1
        d.o("abc", "[abc]");
        d.o("a'b'c", "[a]<b>[c]");
        d.o("'abc'", "<abc>");
        d.o("''", "<>");
        d.o("'", "[']");
        d.o("a'b", "[a'b]");
        d.o("a'b'c'd", "[a]<b>[c'd]");
        d.o(" '  '", "[ ]<  >");
        d.o("'''''", "<><>[']");

        d.o("a'\\'x'b", "[a]<'x>[b]"); // 11
        d.o("a'\\\\'x'b", "[a]<\\>[x'b]"); //
    }

}
