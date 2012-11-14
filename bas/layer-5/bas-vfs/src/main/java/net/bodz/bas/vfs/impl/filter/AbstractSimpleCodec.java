package net.bodz.bas.vfs.impl.filter;

public abstract class AbstractSimpleCodec
        implements ISimpleCodec {

    @Override
    public int encode(int byt) {
        byte[] bytes = { (byte) byt };
        int cb = encode(bytes, 0, 1);
        if (cb == 0)
            return NaN;
        else
            return bytes[0];
    }

    @Override
    public int decode(int byt) {
        byte[] bytes = { (byte) byt };
        int cb = decode(bytes, 0, 1);
        if (cb == 0)
            return NaN;
        else
            return bytes[0];
    }

}
