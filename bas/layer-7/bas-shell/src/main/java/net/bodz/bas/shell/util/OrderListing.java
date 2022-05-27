package net.bodz.bas.shell.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class OrderListing
        implements
            Closeable {

    File orderListFile = new File("lib/order.lst");
    File orderReleaseListFile = new File("lib/order-release.lst");
    File orderDebugListFile = new File("lib/order-debug.lst");
    PrintStream out;
    PrintStream relOut;
    PrintStream dbgOut;

    public OrderListing()
            throws FileNotFoundException {
        out = new PrintStream(orderListFile);
        relOut = new PrintStream(orderReleaseListFile);
        dbgOut = new PrintStream(orderDebugListFile);
    }

    public void add(String s) {
        out.println(s);
        relOut.println(s);
        dbgOut.println(s);
    }

    public void addRelease(String s) {
        out.println(s);
        relOut.println(s);
    }

    public void addDebug(String s) {
        out.println(s);
        dbgOut.println(s);
    }

    @Override
    public void close() {
        out.flush();
        out.close();
        relOut.flush();
        relOut.close();
        dbgOut.flush();
        dbgOut.close();
    }

}
