package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.flow.unit.GenericUnit_11;
import net.bodz.bas.flow.utils.WhatIf;

public class EncodeUnitTest
        extends Assert {

    EncodeUnit encode;
    GenericUnit_11 tostr;

    public EncodeUnitTest()
            throws IOException {
        // bytebuf = [3]
        encode = new EncodeUnit(Charsets.UTF_8.newEncoder(), 3);
        encode.setDst(tostr = new Binary_Hex());
        // encode.setDst(tostr = new Binary_String("ascii"));
    }

    @Test
    public void testOverflow()
            throws IOException {
        class D {
            void o(String in, String expected)
                    throws IOException {
                Collection<Object> out;
                if (in == null)
                    out = WhatIf.send(encode, tostr, "", true);
                else
                    out = WhatIf.send(encode, tostr, in);
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "68 65 6c|6c 6f");
        d.o("你好", "e4 bd a0|e5 a5 bd");
        // x <overflow> 你 <underflow> 好 <underflow>
        d.o("x你好", "78|e4 bd a0|e5 a5 bd");
        d.o("αβγκκκ", "ce b1|ce b2|ce b3|ce ba|ce ba|ce ba");
    }

}
