package net.bodz.bas.text.lop;

import java.io.Reader;

public interface Lexer {

    Token read();

    void reset(Reader in);

    void close();

    int state();

    void enter(int state);

    void leave();

    String getTokenName(int id);

    String getStateName(int state);

}
