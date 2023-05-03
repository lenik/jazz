package net.bodz.bas.t.specmap;

public interface IMutableInetPort
        extends
            IInetPort {

    void setAddress(byte[] address);

    void setAddress(short[] address);

    void setAddress(int[] address);

    void setAddressComponentCount(int count);

    void setAddressComponent(int index, int value);

    void setAddress(String address, char delim, int ordix);

    default void setDotAddress(String address) {
        setAddress(address, '.', DOT_RADIX);
    }

    default void setColonAddress(String address) {
        setAddress(address, ':', COLON_RADIX);
    }

    void setMaskBits(int maskBits);

    void setPort(int port);

}
