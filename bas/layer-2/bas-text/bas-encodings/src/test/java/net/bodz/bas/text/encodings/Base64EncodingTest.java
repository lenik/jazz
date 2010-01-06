package net.bodz.bas.text.encodings;

import static net.bodz.bas.text.encodings.Encodings.BASE64;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Base64EncodingTest {

    @Test
    public void testEncode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                String actual = BASE64.encode(input.getBytes("ascii")); 
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");  
        d.o("hello", "aGVsbG8=");  
        d.o("[\0\n\r\t]", "WwAKDQld");  
    }

    @Test
    public void testDecode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] decode = BASE64.decode(input);
                String actual = new String(decode, "ascii"); 
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");  
        d.o("aGVsbG8=", "hello");  
        d.o("WwAKDQld", "[\0\n\r\t]");  
    }

}
