package net.bodz.bas.text.encodings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HexCharsetTest {

    @Test
    public void testEncode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] bytes = input.getBytes("hex"); 
                String actual = new String(bytes, "ascii"); 
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("", "");  
        d.o("hello", "68 65 6c 6c 6f");  
        d.o("汉字", "6c49 5b57");  
        d.o("123汉4字56", "31 32 33 6c49 34 5b57 35 36");  
    }

    @Test
    public void testDecode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] bytes = input.getBytes("ascii"); 
                String actual = new String(bytes, "hex"); 
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");  
        d.o("68 65 6C 6c 6F", "hello");  
        d.o("6c49 5b57", "汉字");  
        d.o("31 32 33 6c49 34 5b57 35 36", "123汉4字56");  
    }

}
