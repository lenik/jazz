package net.bodz.bas.codec;

import net.bodz.bas.lang.err.EncodeException;

public abstract class _TextCodec<T> implements TextCodec<T> {

    @Override
    public Class<? extends String> getEncodedType() {
        return String.class;
    }

    @Override
    public String encode(T value) throws EncodeException {
        return String.valueOf(value);
    }

}
