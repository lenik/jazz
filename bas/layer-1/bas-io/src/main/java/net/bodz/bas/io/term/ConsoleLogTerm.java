package net.bodz.bas.io.term;



public class ConsoleLogTerm extends LogTerm {

    @Override
    public ITerminal filter(int level) {
        if (level <= WARN)
            return Terminals.stderr;
        return Terminals.stdout;
    }

}
