package net.bodz.bas.commons.codec;

import net.bodz.bas.api.exceptions.DecodeException;
import net.bodz.bas.api.exceptions.EncodeException;

public interface Codec<T, E> {

    Class<? extends E> getEncodedType();

    Class<? extends T> getDecodedType();

    E encode(T value) throws EncodeException;

    T decode(E encoded) throws DecodeException;

}
