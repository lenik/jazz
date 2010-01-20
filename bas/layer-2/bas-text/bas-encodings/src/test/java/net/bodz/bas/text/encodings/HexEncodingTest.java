package net.bodz.bas.text.encodings;

import static net.bodz.bas.text.codec.Encodings.HEX;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HexEncodingTest {

    @Test
    public void testEncode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                String actual = HEX.encode(input.getBytes("ascii")); 
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //  
        d.o("hello", "68 65 6c 6c 6f"); //  
        d.o("[\0\n\r\t]", "5b 00 0a 0d 09 5d"); //  
    }

    @Test
    public void testDecode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] decode = HEX.decode(input);
                String actual = new String(decode, "ascii"); 
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //  
        d.o("68 65 6c 6c 6f", "hello"); //  
        d.o("5b 00 0a 0d 09 5d", "[\0\n\r\t]"); //  
    }

}
