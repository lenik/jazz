package net.bodz.dist.ins.builtins;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class FileStorage {

    public InputStream loadFrom(String name) {
        InputStream in = null;
        return in;
    }

    public PrintStream dumpTo(String name, String encoding) {
        OutputStream out = null;
        try {
            PrintStream ps = new PrintStream(out, false, encoding);
            return ps;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
