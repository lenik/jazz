package net.bodz.bas.types.parsers;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import net.bodz.bas.io.term.StreamTerminal;
import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.log.LogTerm;
import net.bodz.bas.types.TypeParser;

public class LoggerParser implements TypeParser {

    @Override
    public LogTerm parse(String path) throws ParseException {
        PrintStream out;
        try {
            out = new PrintStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        final StreamTerminal sterm = new StreamTerminal(out);
        return new LogTerm() {
            @Override
            public Terminal filter(int level) {
                return sterm;
            }
        };
    }

}
