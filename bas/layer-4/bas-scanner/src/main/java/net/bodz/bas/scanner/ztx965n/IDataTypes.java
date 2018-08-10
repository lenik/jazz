package net.bodz.bas.scanner.ztx965n;

import net.bodz.bas.data.codec.builtin.DecimalCodec;
import net.bodz.bas.data.codec.builtin.HexCodec;

public interface IDataTypes {

    DecimalCodec netCodec = new DecimalCodec(".");
    HexCodec macCodec = new HexCodec(":");
    HexCodec memCodec = new HexCodec("");

    class data {
        public static int encodeBaudRate(int bps) {
            switch (bps) {
            case 9600:
                return 4;
            case 19200:
                return 5;
            case 38400:
                return 6;
            case 57600:
                return 7;
            case 115200:
                return 8;
            default:
                throw new IllegalArgumentException("Not supported baud rate: " + bps);
            }
        }
    }

}
