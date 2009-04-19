package net.bodz.bas.util;

import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.io.term.Terminals;

public class ConsoleLogTerm extends LogTerm {

    @Override
    public Terminal filter(int level) {
        if (level <= WARN)
            return Terminals.stderr;
        return Terminals.stdout;
    }

}
