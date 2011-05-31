package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.flow.units.SISOUnit;
import net.bodz.bas.flow.util.WhatIf;
import net.bodz.bas.string.StringArray;
import net.bodz.bas.text.codec.builtin.HexCodec;

public class DecodeUnitTest
        extends Assert {

    DecodeUnit decode;
    SISOUnit tostr;

    public DecodeUnitTest()
            throws IOException {
        Charset utf8 = Charset.forName("utf-8");
        // charbuf = [3]
        decode = new DecodeUnit(utf8.newDecoder(), 3);
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
                    out = WhatIf.send(decode, tostr, ArrayUtils.EMPTY_BYTE_ARRAY, true);
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
