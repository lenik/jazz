package net.bodz.bas.scanner.ep6000;

import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.io.BByteOut;

public class SerialEp6000Test {

    static HexCodec hex = new HexCodec(" ", "\n", 16).padding(2, '0');

    public static void main(String[] args)
            throws Exception {
        SerialEp6000 s = new SerialEp6000();

        BByteOut buf = new BByteOut();

        L: while (true) {
            s.startDecode();
            while (true) {
                int ch = s.in.read();
                if (ch == -1)
                    break L;

                buf.write(ch);

                if (ch == '\n') {
                    byte[] pkt = buf.flip();
                    System.out.println(hex.encode(pkt));
                    System.out.println(new String(pkt));
                    break;
                }
            }
        }

        s.close();
    }

}
