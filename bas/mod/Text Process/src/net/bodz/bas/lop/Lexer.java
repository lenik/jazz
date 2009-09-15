package net.bodz.bas.lop;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.lop.util.XYTellable;

public interface Lexer extends XYTellable {

    Token read();

    /** offset of start of match, 0-based */
    long tell() throws IOException;

    /** line of start of match, 0-based */
    int tellY();

    /** column of start of match, 0-based */
    int tellX();

    void reset(Reader in);

    void close();

    int state();

    void enter(int state);

    void leave();

    String getTokenName(int id);

    String getStateName(int state);

}
