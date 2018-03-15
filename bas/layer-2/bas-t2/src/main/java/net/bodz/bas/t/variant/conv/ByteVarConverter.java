package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class ByteVarConverter
        extends AbstractVarConverter<Byte> {

    public ByteVarConverter() {
        super(Byte.class);
    }

    @Override
    public Byte fromNumber(Number in)
            throws TypeConvertException {
        return in.byteValue();
    }

    @Override
    public Byte fromString(String in)
            throws TypeConvertException {
        // TODO auto radix.
        return Byte.valueOf(in);
    }

    @Override
    public Byte fromByteArray(byte[] in) {
        if (in.length == 0)
            return null;
        else
            return in[0];
    }

    @Override
    public Byte toNumber(Byte value) {
        return value;
    }

    @Override
    public byte toByte(Byte value) {
        return value.byteValue();
    }

    @Override
    public short toShort(Byte value) {
        return (short) (value.byteValue() & 0xff);
    }

    @Override
    public int toInt(Byte value) {
        return value.byteValue() & 0xff;
    }

    @Override
    public long toLong(Byte value) {
        return value.byteValue() & 0xff;
    }

    @Override
    public float toFloat(Byte value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(Byte value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(Byte value) {
        return value.intValue() != 0;
    }

    public static final ByteVarConverter INSTANCE = new ByteVarConverter();

}
