package net.bodz.bios.units.text;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.text.util.Strings;
import net.bodz.bios.util.WhatIf;

import org.junit.Test;

public class BreakOrCutLinesUnitTest {

    BreakOrCutLinesUnit nochop;
    {
        nochop = new BreakOrCutLinesUnit(3);
        nochop.setChop(false);
    }

    @Test
    public void testNochopCont() throws IOException {
        class D {
            void o(String in, String expected) throws IOException {
                Collection<Object> out;
                if (in == null)
                    out = WhatIf.send(nochop, "", true); 
                else
                    out = WhatIf.send(nochop, in);
                String actual = Strings.join("|", out); 
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("12345", "123");  
        d.o("67890", "456|789");  
        d.o("x\n1\n12\n123\n", "0x\n|1\n|12\n|123|\n");  
        d.o("abcdef", "abc|def");  
        d.o("ghij", "ghi");  
        d.o(null, "j"); 
    }

}
