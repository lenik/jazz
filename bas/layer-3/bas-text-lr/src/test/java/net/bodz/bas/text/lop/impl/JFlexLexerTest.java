package net.bodz.bas.text.lop.impl;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.IXYTellable;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.text.lop.Token;

public class JFlexLexerTest
        extends Assert {

    static class TD {

        int id;
        String text;
        Object value;

        public TD(int id) {
            this(id, null, null);
        }

        public TD(int id, Object value) {
            this(id, null, value);
        }

        public TD(int id, String text, Object value) {
            this.id = id;
            this.text = text;
            this.value = value;
        }

    }

    void test(String text, TD[] testSeq) {
        StringReader reader = new StringReader(text);
        SampleLexer lexer = new SampleLexer(reader);
        int i = 0;
        Token actual;
        while ((actual = lexer.read()) != null) {
            IXYTellable prev = lexer.getTokenStart(1);
            System.out.printf("Read: %s .. %s\n", prev, actual);
            if (i >= testSeq.length)
                break;
            TD expected = testSeq[i++];
            assertEquals(expected.id, actual.getId());
            if (expected.text != null)
                assertEquals(expected.text, actual.getText());
            if (expected.value != null)
                assertEquals(expected.value, actual.getValue());
        }
    }

    @Test
    public void test1()
            throws Exception {
        String text = ClassResource.getData(getClass(), "1")//
                .tooling()._for(StreamReading.class).readString();
        TD[] expected = {
                //
                new TD(SampleLexer.ID, "hello", "hello"), //
                new TD(','), //
                new TD(SampleLexer.ID, "world", "world"), //
                new TD(SampleLexer.ID, "a", "a"), //
                new TD('='), //
                new TD(SampleLexer.NUMBER, "123", 123), //
                new TD(';'), //
                new TD(SampleLexer.ID, "b", "b"), //
                new TD('='), //
                new TD(SampleLexer.STRING, null, "ok, done"), //
                new TD(';'), //
        };
        test(text, expected);
    }

}
