package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.io.term.ITerminal;
import net.bodz.bas.io.term.LogTerm;
import net.bodz.bas.io.term.StreamTerminal;
import net.bodz.bas.type.traits.AbstractParser;

public class LoggerParser
        extends AbstractParser<LogTerm> {

    @Override
    public LogTerm parse(String path)
            throws ParseException {
        PrintStream out;
        try {
            out = new PrintStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        final StreamTerminal sterm = new StreamTerminal(out);
        return new LogTerm() {
            @Override
            public ITerminal filter(int level) {
                return sterm;
            }
        };
    }

}
