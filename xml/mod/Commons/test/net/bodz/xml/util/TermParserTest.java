package net.bodz.xml.util;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.util.Arrays2;

import org.junit.Test;

public class TermParserTest {

    @Test
    public void testParameters() throws ParseException {
        Iterable<Term> terms = TermParser.parse(null,
                "A() B(1) C(abc) D(a,) D(a ,) D( a  ,) D(a, )"
                        + "E(,a) E(  ,a) E(,   a) E( , a ) F(,) F(  ,) F(,  ) F( , ) G(,,)");
        Term[] tv = Arrays2.convert(terms, new Term[0]);
        for (int i = 0; i < tv.length; i++) {
            System.out.println(tv[i]);
            // System.out.printf("assertEquals(\"%s\", tv[%d].toString()); \n", //
            // tv[i].toString(), i);
        }
        assertEquals("A()", tv[0].toString());
        assertEquals("B(1)", tv[1].toString());
        assertEquals("C(abc)", tv[2].toString());
        assertEquals("D(a, )", tv[3].toString());
        assertEquals("D(a, )", tv[4].toString());
        assertEquals("D(a, )", tv[5].toString());
        assertEquals("D(a, )", tv[6].toString());
        assertEquals("E(, a)", tv[7].toString());
        assertEquals("E(, a)", tv[8].toString());
        assertEquals("E(, a)", tv[9].toString());
        assertEquals("E(, a)", tv[10].toString());
        assertEquals("F(, )", tv[11].toString());
        assertEquals("F(, )", tv[12].toString());
        assertEquals("F(, )", tv[13].toString());
        assertEquals("F(, )", tv[14].toString());
        assertEquals("G(, , )", tv[15].toString());
    }

    @Test
    public void testMisc() throws ParseException {
        Iterable<Term> terms = TermParser.parse(null,
                "rrArB(mx->6,,wh:ss)[, 4 ,]F<bmp>(clustered,unique)HiK L9L18M");
        Term[] tv = Arrays2.convert(terms, new Term[0]);
        for (int i = 0; i < tv.length; i++) {
            System.out.println(tv[i]);
            // System.out.printf("assertEquals(\"%s\", tv[%d].toString()); \n", //
            // tv[i].toString(), i);
        }
        assertEquals("rr", tv[0].toString());
        assertEquals("Ar", tv[1].toString());
        assertEquals("B(mx->6, , wh:ss)[,4,]", tv[2].toString());
        assertEquals("F<bmp>(clustered, unique)", tv[3].toString());
        assertEquals("Hi", tv[4].toString());
        assertEquals("K", tv[5].toString());
        assertEquals("L9", tv[6].toString());
        assertEquals("L18", tv[7].toString());
        assertEquals("M", tv[8].toString());
    }

}
