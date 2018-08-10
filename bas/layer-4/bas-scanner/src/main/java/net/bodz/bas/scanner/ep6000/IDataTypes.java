package net.bodz.bas.scanner.ep6000;

import net.bodz.bas.data.codec.builtin.DecimalCodec;
import net.bodz.bas.data.codec.builtin.HexCodec;

public interface IDataTypes {

    DecimalCodec netCodec = new DecimalCodec(".");
    HexCodec macCodec = new HexCodec(":");
    HexCodec memCodec = new HexCodec("");

    class data {

        public static int encodeBaudRate(int bps) {
            switch (bps) {
            case 4800:
                return 0;
            case 9600:
                return 1;
            case 19200:
                return 2;
            case 38400:
                return 3;
            case 51200:
                return 4;
            case 115200:
                return 6;
            default:
                throw new IllegalArgumentException("Not supported baud rate: " + bps);
            }
        }

        public static int encodeRs232Mode(boolean fullByte, int stopBits, ParityCheckMode parity) {
            if (parity == null)
                throw new NullPointerException("parity");

            int mode = fullByte ? 6 : 0;
            if (stopBits == 2)
                mode += 3;
            else if (stopBits != 1)
                throw new IllegalArgumentException("Invalid stop bits: " + stopBits);

            if (parity == ParityCheckMode.EVEN)
                mode += 1;
            else if (parity == ParityCheckMode.ODD)
                mode += 2;
            return mode;
        }

    }

}
