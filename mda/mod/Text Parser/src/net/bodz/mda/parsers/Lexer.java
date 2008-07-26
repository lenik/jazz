package net.bodz.mda.parsers;

import java.io.IOException;
import java.io.Reader;

import net.bodz.mda.parsers.io.DocumentPosition;

public interface Lexer extends DocumentPosition {

    Token read();

    /** offset of start of match, 0-based */
    long tell() throws IOException;

    /** line of start of match, 0-based */
    int line();

    /** column of start of match, 0-based */
    int column();

    void reset(Reader in);

    void close();

    int state();

    void enter(int state);

    void leave();

    String getTokenName(int id);

    String getStateName(int state);

}
