package net.bodz.bas.io.term;

import net.bodz.bas.io.out.CharOuts;

public class Terminals {

    public static final ITerminal nil = new NullTerminal();

    public static final ConsoleTerminal stdout;
    public static final ConsoleTerminal stderr;
    static {
        int width = 80;
        stdout = new ConsoleTerminal(CharOuts.stdout, width);
        stderr = new ConsoleTerminal(CharOuts.stderr, width);
    }

}
