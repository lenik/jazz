package net.bodz.mda.parsers;

import java.io.Reader;

public class JFlexLexerTest extends JFlexLexer {

    public static final int NUM   = 300;
    public static final int WORD  = 301;
    public static final int ARROW = 302;

    public JFlexLexerTest(Reader in) {
        super(in);
    }

}
