package net.bodz.bas.io.typeparsers;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;

public class LoggerParser extends TypeParser {

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
