package net.bodz.bas.t.specmap;

/**
 * Inet address and port.
 */
public interface IInetPort {

    byte[] getAddress8();

    short[] getAddress16();

    int[] getAddress32();

    int getAddressComponentCount();

    int getAddressComponent(int index);

    default String getAddress(char delim, int ordix) {
        StringBuilder sb = new StringBuilder();
        int n = getAddressComponentCount();
        for (int i = 0; i < n; i++) {
            if (i != 0)
                sb.append(delim);
            int component = getAddressComponent(i);
            String s = Integer.toString(component, ordix);
            sb.append(s);
        }
        return sb.toString();
    }

    int DOT_RADIX = 10;
    int COLON_RADIX = 16;

    default String getDotAddress() {
        return getAddress('.', DOT_RADIX);
    }

    default String getColonAddress() {
        return getAddress(':', COLON_RADIX);
    }

    int getMaskBits();

    int getPort();

}
