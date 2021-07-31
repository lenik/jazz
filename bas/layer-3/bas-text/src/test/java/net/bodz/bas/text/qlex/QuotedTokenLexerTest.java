package net.bodz.bas.text.qlex;

import java.io.IOException;

import org.junit.Assert;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.StringCharIn;
import net.bodz.bas.text.qlex.ILa1CharIn;
import net.bodz.bas.text.qlex.La1CharInImpl;
import net.bodz.bas.text.qlex.QuotableTokenLexer;

public class QuotedTokenLexerTest
        extends Assert {

    public static void main(String[] args)
            throws IOException, ParseException {
        QuotableTokenLexer lexer = new QuotableTokenLexer(true);
        String s = "hello 'a b'\tand     some \\x41\\x42 and \\u0037\\u0039so all.";
        StringCharIn in = new StringCharIn(s);
        ILa1CharIn la = new La1CharInImpl(in);
        int c;
        while ((c = la.look()) != -1) {
            if (Character.isWhitespace(c)) {
                la.read();
                continue;
            }
            String token = lexer.lex(la);
            System.out.println("token: [" + token + "]");
        }
    }

}
