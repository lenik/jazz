package net.bodz.bios.units.text;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;
import net.bodz.bios.units.SISOUnit;
import net.bodz.bios.util.WhatIf;

import org.junit.Test;

public class EncodeUnitTest {

    EncodeUnit encode;
    SISOUnit   tostr;

    public EncodeUnitTest() throws IOException {
        Charset utf8 = Charset.forName("utf-8");
        // bytebuf = [3]
        encode = new EncodeUnit(utf8.newEncoder(), 3);
        encode.setDst(tostr = new Binary_Hex());
        // encode.setDst(tostr = new Binary_String("ascii"));
    }

    @Test
    public void testOverflow() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out;
                if (in == null)
                    out = WhatIf.send(encode, tostr, "", true);
                else
                    out = WhatIf.send(encode, tostr, in);
                return Strings.join("|", out);
            }
        }, //
                EQ("hello", "68 65 6c|6c 6f"), //
                EQ("你好", "e4 bd a0|e5 a5 bd"), //
                // x <overflow> 你 <underflow> 好 <underflow>
                EQ("x你好", "78|e4 bd a0|e5 a5 bd"), //
                EQ("αβγκκκ", "ce b1|ce b2|ce b3|ce ba|ce ba|ce ba"), //
                END);
    }

}
