package net.bodz.bas.data.codec.builtin;

import org.junit.Assert;
import org.junit.Test;

public class DecimalCodecTest
        extends Assert {

    DecimalCodec codec = new DecimalCodec();

    @Test
    public void testEncode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("hello", "104 101 108 108 111"); //
        d.o("[\0\n\r\t]", "91 00 10 13 09 93"); //
    }

    @Test
    public void testRowSep_1_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1, ":", 1);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "104"); //
        d.o("hello", "104:101:108:108:111"); //
    }

    @Test
    public void testRowSep1_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1, ":", 2);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "104"); //
        d.o("hello", "104,101:108,108:111"); //
    }

    @Test
    public void testRowSep2_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2, ":", 1);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "104"); //
        d.o("hello", "26725:27756:111"); //
    }

    @Test
    public void testRowSep2_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2, ":", 2);
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "104"); //
        d.o("helloWorld", "26725,27756:28503,28530:27748"); //
    }

    @Test
    public void testPadding1_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1);
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
        d.o("h", "104"); //
        d.o("\u0001\u0033\u000c\u0078\u0000\u0064", "1,51,12,120,0,100"); //
    }

    @Test
    public void testPadding1_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2);
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
        d.o("h", "104"); //
        d.o("\u0000\u0033\u000c\u0078\u0000\u0064", "51,3192,100"); //
    }

    @Test
    public void testPadding4_1()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 1);
        codec.padding(4, '*');
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "*104"); //
        d.o("hello", "*104,*101,*108,*108,*111"); //
    }

    @Test
    public void testPadding6_2()
            throws Exception {
        final DecimalCodec codec = new DecimalCodec(",", 2);
        codec.padding(6, '*');
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("h", "***104"); //
        d.o("hello", "*26725,*27756,***111"); //
    }

    @Test
    public void testDecode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                byte[] decode = codec.decode(input);
                String actual = new String(decode, "ascii");
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //
        d.o("104 101 108 108 111", "hello"); //
        d.o("91 00 10 13 09 93", "[\0\n\r\t]"); //
    }

}
