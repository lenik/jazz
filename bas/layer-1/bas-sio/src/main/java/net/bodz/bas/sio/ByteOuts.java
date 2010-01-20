package net.bodz.bas.sio;

public class ByteOuts {

    public static final NullByteOut nil = NullByteOut.getInstance();
    public static final OutputStreamByteOut stdout = new OutputStreamByteOut(System.out);
    public static final OutputStreamByteOut stderr = new OutputStreamByteOut(System.err);

}
