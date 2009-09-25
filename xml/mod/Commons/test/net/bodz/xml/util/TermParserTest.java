package net.bodz.xml.util;

import net.bodz.bas.lang.err.ParseException;

import org.junit.Test;

public class TermParserTest {

    @Test
    public void test1() throws ParseException {
        for (Term term : TermParser.parse(null, "rArB(mx->6, wh:ss)[,4,]FHiKL9LM")) {
            System.out.println(term);
        }
    }

}
