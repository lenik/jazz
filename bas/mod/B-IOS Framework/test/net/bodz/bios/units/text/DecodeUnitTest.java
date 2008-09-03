package net.bodz.bios.units.text;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Strings;
import net.bodz.bios.units.SISOUnit;
import net.bodz.bios.util.WhatIf;

import org.junit.Test;

public class DecodeUnitTest {

    DecodeUnit decode;
    SISOUnit   tostr;

    public DecodeUnitTest() throws IOException {
        Charset utf8 = Charset.forName("utf-8");
        // charbuf = [3]
        decode = new DecodeUnit(utf8.newDecoder(), 3);
        decode.setDst(tostr = new Text_String());
    }

    @Test
    public void testOverflow() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String inHex) throws Throwable {
                if (isBreakpoint())
                    System.err.println(inHex);
                Collection<Object> out;
                if (inHex == null)
                    out = WhatIf.send(decode, tostr, Empty.bytes, true);
                else {
                    byte[] in = Encodings.HEX.decode(inHex);
                    out = WhatIf.send(decode, tostr, in);
                }
                return Strings.join("|", out);
            }
        }, //
                EQ("68 65 6c 6c 6f", "hel|lo"), //
                EQ("e4bda0 e5a5bd", "你|好"), //
                // x <overflow> 你 <underflow> 好 <underflow>
                EQ("78 e4bda0 e5a5bd", "x|你|好"), //
                EQ("ceb1 ceb2 ceb3 ceba ceba ceba", "α|β|γ|κ|κ|κ"), //
                END);
    }

}
