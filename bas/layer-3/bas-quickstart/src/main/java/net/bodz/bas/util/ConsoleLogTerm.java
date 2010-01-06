package net.bodz.bas.util;


public class ConsoleLogTerm extends LogTerm {

    @Override
    public Terminal filter(int level) {
        if (level <= WARN)
            return Terminals.stderr;
        return Terminals.stdout;
    }

}
