package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.flow.unit.GenericUnit_11;
import net.bodz.bas.flow.utils.WhatIf;

public class DecodeUnitTest
        extends Assert {

    DecodeUnit decode;
    GenericUnit_11 tostr;

    public DecodeUnitTest()
            throws IOException {
        // charbuf = [3]
        decode = new DecodeUnit(Charsets.UTF8.newDecoder(), 3);
        decode.setDst(tostr = new Text_String());
    }

    static HexCodec hexCodec = HexCodec.getInstance();

    @Test
    public void testOverflow()
            throws Exception {
        class D {
            void o(String inHex, String expected)
                    throws Exception {
                Collection<Object> out;
                if (inHex == null)
                    out = WhatIf.send(decode, tostr, new byte[0], true);
                else {
                    byte[] in = hexCodec.decode(inHex);
                    out = WhatIf.send(decode, tostr, in);
                }
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("68 65 6c 6c 6f", "hel|lo");
        d.o("e4bda0 e5a5bd", "你|好");
        // x <overflow> 你 <underflow> 好 <underflow>
        d.o("78 e4bda0 e5a5bd", "x|你|好");
        d.o("ceb1 ceb2 ceb3 ceba ceba ceba", "α|β|γ|κ|κ|κ");
    }

}
