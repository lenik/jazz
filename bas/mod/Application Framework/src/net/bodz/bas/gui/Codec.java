package net.bodz.bas.gui;

public interface Codec {

    Class<?> getEncodedType();

    Object encode(Object value);

    Object decode(Object encoded);

}
