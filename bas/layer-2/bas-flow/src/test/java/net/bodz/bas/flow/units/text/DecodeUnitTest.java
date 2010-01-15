package net.bodz.bas.flow.units.text;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import net.bodz.bas.flow.units.SISOUnit;
import net.bodz.bas.flow.units.builtin.text.DecodeUnit;
import net.bodz.bas.flow.units.builtin.text.Text_String;
import net.bodz.bas.flow.util.WhatIf;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class DecodeUnitTest {

    DecodeUnit decode;
    SISOUnit tostr;

    public DecodeUnitTest() throws IOException {
        Charset utf8 = Charset.forName("utf-8"); 
        // charbuf = [3]
        decode = new DecodeUnit(utf8.newDecoder(), 3);
        decode.setDst(tostr = new Text_String());
    }

    @Test
    public void testOverflow() throws Exception {
        class D {
            void o(String inHex, String expected) throws Exception {
                Collection<Object> out;
                if (inHex == null)
                    out = WhatIf.send(decode, tostr, Empty.bytes, true);
                else {
                    byte[] in = Encodings.HEX.decode(inHex);
                    out = WhatIf.send(decode, tostr, in);
                }
                String actual = Strings.join("|", out); 
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
