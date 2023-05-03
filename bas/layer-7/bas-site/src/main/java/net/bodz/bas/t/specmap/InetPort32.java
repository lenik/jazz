package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ParseException;

public class InetPort32
        extends MutableInetPort {

    static final int COMPONENT_MAX = 128;

    int[] address;

    public InetPort32(int componentCount) {
        setAddressComponentCount(componentCount);
    }

    public InetPort32(int[] address) {
        setAddress(address);
    }

    @Override
    public final byte[] getAddress8() {
        byte[] v = new byte[address.length];
        for (int i = 0; i < address.length; i++)
            v[i] = (byte) address[i];
        return v;
    }

    @Override
    public final short[] getAddress16() {
        short[] v = new short[address.length];
        for (int i = 0; i < address.length; i++)
            v[i] = (short) address[i];
        return v;
    }

    @Override
    public final int[] getAddress32() {
        return address;
    }

    @Override
    public final int getAddressComponentCount() {
        return address.length;
    }

    @Override
    public final int getAddressComponent(int index) {
        return address[index];
    }

    @Override
    public final void setAddress(byte[] address) {
        if (address == null)
            throw new NullPointerException("address");
        int[] v = new int[address.length];
        for (int i = 0; i < address.length; i++)
            v[i] = address[i] & 0xFF;
        this.address = v;
    }

    @Override
    public final void setAddress(short[] address) {
        if (address == null)
            throw new NullPointerException("address");
        int[] v = new int[address.length];
        for (int i = 0; i < address.length; i++)
            v[i] = address[i] & 0xFFFF;
        this.address = v;
    }

    @Override
    public final void setAddress(int[] address) {
        if (address == null)
            throw new NullPointerException("address");
        this.address = address;
    }

    @Override
    public final void setAddressComponentCount(int count) {
        if (count <= 0 || count > COMPONENT_MAX)
            throw new IllegalArgumentException("invalid count: " + count);
        this.address = new int[count];
    }

    @Override
    public final void setAddressComponent(int index, int value) {
        this.address[index] = value;
    }

    @Override
    public final void setAddress(String address, char delim, int ordix) {
        int[] v = new int[this.address.length];
        int len = address.length();
        int end = 0;
        int index = 0;
        while (end < len) {
            if (index >= v.length)
                throw new IllegalArgumentException("too many address components: " + address);
            int next = address.indexOf(delim, end);
            String sub;
            if (next == -1) {
                sub = address.substring(end);
                next = end;
            } else {
                sub = address.substring(end, next);
                end = next + 1;
            }
            int component = Integer.parseInt(sub, ordix);
            v[index++] = component;
        }
        if (index != v.length)
            throw new IllegalArgumentException("insufficient address components: " + address);
        this.address = v;
    }

    public static InetPort32 parse(String ipPort)
            throws ParseException {
        int colon = ipPort.indexOf(':');
        String ip = ipPort;
        String mask = null;
        String port = null;
        if (colon != -1) {
            ip = ipPort.substring(0, colon);
            port = ipPort.substring(colon + 1);
        }

        int slash = ip.lastIndexOf('/');
        if (slash != 0) {
            mask = ip.substring(slash + 1);
            ip = ip.substring(0, slash);
        }

        InetPort32 ap = new InetPort32(4);
        ap.setDotAddress(ip);
        if (mask != null) {
            int maskBits = Integer.parseInt(mask);
            ap.setMaskBits(maskBits);
        }

        if (port != null)
            try {
                int portNum = Integer.parseInt(port);
                ap.setPort((short) portNum);
            } catch (NumberFormatException e) {
                throw new ParseException(e);
            }
        return ap;
    }

    public static InetPort32 parse6(String ipPort)
            throws ParseException {
        String ip;
        String port;
        if (ipPort.startsWith("[")) { // has port number
            int rb = ipPort.indexOf(1, ']');
            if (rb == -1)
                throw new ParseException("Found '[', but without ']': " + ipPort);
            ip = ipPort.substring(1, rb);
            String remain = ipPort.substring(rb + 1);
            if (!remain.isEmpty()) {
                if (!remain.startsWith(":"))
                    throw new ParseException("expect ':': " + remain);
                port = remain.substring(1);
            } else {
                port = null;
            }
        } else {
            ip = ipPort;
            port = null;
        }

        int[] address = IPv6Utils.parse(ip);
        InetPort32 ap = new InetPort32(8);
        ap.address = address;
        if (port != null)
            try {
                int portNum = Integer.parseInt(port);
                ap.setPort((short) portNum);
            } catch (NumberFormatException e) {
                throw new ParseException(e);
            }
        return ap;
    }

}
