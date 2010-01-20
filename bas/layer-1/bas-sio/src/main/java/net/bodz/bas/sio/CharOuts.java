package net.bodz.bas.sio;

public class CharOuts {

    public static final NullCharOut nil = NullCharOut.getInstance();
    public static final PrintStreamCharOut stdout = new PrintStreamCharOut(System.out);
    public static final PrintStreamCharOut stderr = new PrintStreamCharOut(System.err);

}
