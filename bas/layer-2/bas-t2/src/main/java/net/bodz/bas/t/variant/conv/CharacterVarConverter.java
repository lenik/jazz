package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class CharacterVarConverter
        extends AbstractVarConverter<Character> {

    public CharacterVarConverter() {
        super(Character.class);
    }

    @Override
    public Character fromNumber(Number in)
            throws TypeConvertException {
        int code = in.intValue();
        return (char) (code & 0xFFFF);
    }

    @Override
    public Character fromString(String in)
            throws TypeConvertException {
        if (in.isEmpty())
            return null;
        else
            return in.charAt(0);
    }

    @Override
    public Number toNumber(Character value) {
        int n = value.charValue();
        return n;
    }

    @Override
    public byte toByte(Character value) {
        return (byte) value.charValue();
    }

    @Override
    public short toShort(Character value) {
        return (short) value.charValue();
    }

    @Override
    public int toInt(Character value) {
        return value.charValue();
    }

    @Override
    public long toLong(Character value) {
        return value.charValue();
    }

    @Override
    public float toFloat(Character value) {
        return value.charValue();
    }

    @Override
    public double toDouble(Character value) {
        return value.charValue();
    }

    @Override
    public boolean toBoolean(Character value) {
        return value.charValue() != 0;
    }

    @Override
    public char toChar(Character value) {
        return value.charValue();
    }

    public static final CharacterVarConverter INSTANCE = new CharacterVarConverter();

}
