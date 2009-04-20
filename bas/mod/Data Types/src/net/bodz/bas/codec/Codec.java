package net.bodz.bas.codec;

import net.bodz.bas.lang.err.DecodeException;
import net.bodz.bas.lang.err.EncodeException;

public interface Codec<T, E> {

    Class<? extends E> getEncodedType();

    Class<? extends T> getDecodedType();

    E encode(T value) throws EncodeException;

    T decode(E encoded) throws DecodeException;

}
