package net.bodz.bas.io.term;

import net.bodz.bas.io.CharOuts;

public class Terminals {

    public static final Terminal        nil = new NullTerminal();

    public static final ConsoleTerminal stdout;
    public static final ConsoleTerminal stderr;
    static {
        int width = 80;
        stdout = new ConsoleTerminal(CharOuts.stdout, width);
        stderr = new ConsoleTerminal(CharOuts.stderr, width);
    }

}
