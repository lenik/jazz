package net.bodz.bas.flow.units.builtin.adapters;

import java.nio.CharBuffer;

public class CharBuffer_In {

    public Object convert(CharBuffer in, Class<?> targetType) {
        if (char[].class.isAssignableFrom(targetType))
            return in.array();
        throw new UnsupportedOperationException();
    }

}
