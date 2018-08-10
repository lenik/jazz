package net.bodz.bas.data.codec.builtin;

import org.junit.Assert;
import org.junit.Test;

public class DecimalHexCodecTest
        extends Assert {

    DecimalCodec hexCodec = new DecimalCodec().radix(16);

    @Test
    public void testEncode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = hexCodec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("hello", "68 65 6c 6c 6f"); //
        d.o("[\0\n\r\t]", "5b 00 0a 0d 09 5d"); //
    }

    @Test
    public void testRowSep()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1, ":", 1).radix(16);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "68"); //
        d.o("hello", "68:65:6c:6c:6f"); //
    }

    @Test
    public void testRowSep1_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1, ":", 2).radix(16);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "68"); //
        d.o("hello", "68,65:6c,6c:6f"); //
    }

    @Test
    public void testRowSep2_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2, ":", 1).radix(16);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "68"); //
        d.o("hello", "6865:6c6c:6f"); //
    }

    @Test
    public void testRowSep2_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2, ":", 2).radix(16);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "68"); //
        d.o("helloWorld", "6865,6c6c:6f57,6f72:6c64"); //
    }

    @Test
    public void testPadding1_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1).radix(16);
        codec.padding(1, '*');
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "68"); //
        d.o("\u0001\u0033\u000c\u0078\u0000\u0064", "1,33,c,78,0,64"); //
    }

    @Test
    public void testPadding1_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2).radix(16);
        codec.padding(1, '*');
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "68"); //
        d.o("\u0000\u0033\u000c\u0078\u0000\u0064", "33,c78,64"); //
    }

    @Test
    public void testPadding3_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1).radix(16);
        codec.padding(3, '*');
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "*68"); //
        d.o("hello", "*68,*65,*6c,*6c,*6f"); //
    }

    @Test
    public void testPadding3_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2).radix(16);
        codec.padding(3, '*');
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "*68"); //
        d.o("hello", "6865,6c6c,*6f"); //
    }

    @Test
    public void testDecode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                byte[] decode = hexCodec.decode(input);
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
