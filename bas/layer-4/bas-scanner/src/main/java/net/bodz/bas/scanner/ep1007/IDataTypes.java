package net.bodz.bas.scanner.ep1007;

import net.bodz.bas.data.codec.builtin.DecimalCodec;
import net.bodz.bas.data.codec.builtin.HexCodec;

public interface IDataTypes {

    DecimalCodec netCodec = new DecimalCodec(".");
    HexCodec macCodec = new HexCodec(":");
    HexCodec memCodec = new HexCodec("");

}
