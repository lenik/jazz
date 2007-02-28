package net.bodz.xml.util;

import net.sf.freejava.err.ParseException;

import org.junit.Test;

public class TermsParserTest {

    @Test
    public void test1() throws ParseException {
        Term[] terms = TermsParser.parse("rArB(mx->6, wh:ss)[,4,]FHiKL9LM");
        for (Term term : terms)
            System.out.println(term);
    }

    public static void main(String[] args) throws Exception {
        TermsParserTest test = new TermsParserTest();
        test.test1();
    }

}
