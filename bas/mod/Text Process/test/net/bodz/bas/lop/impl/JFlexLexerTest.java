package net.bodz.bas.lop.impl;

import java.io.StringReader;

import net.bodz.bas.io.Files;
import net.bodz.bas.lop.Token;

import org.junit.Test;

public class JFlexLexerTest {

    void test(String text, Object[] tokens) {
        StringReader reader = new StringReader(text);
        SampleLexer lexer = new SampleLexer(reader);
        Token token;
        while ((token = lexer.read()) != null) {
            System.out.println(token);
        }
    }

    @Test
    public void test1() throws Exception {
        String text = Files.readAll(Files.classData(getClass(), "1"));
        test(text, null);
    }

}
