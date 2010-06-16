package net.bodz.bas.log.term;

import net.bodz.bas.sio.Stdio;

public class Terminals {

    public static final ITerminal nil = new NullTerminal();

    public static final ConsoleTerminal stdout;
    public static final ConsoleTerminal stderr;
    static {
        int width = 80;
        stdout = new ConsoleTerminal(Stdio.cout, width);
        stderr = new ConsoleTerminal(Stdio.cerr, width);
    }

}
