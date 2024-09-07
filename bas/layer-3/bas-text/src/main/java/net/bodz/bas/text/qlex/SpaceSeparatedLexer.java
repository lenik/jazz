package net.bodz.bas.text.qlex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;

public class SpaceSeparatedLexer
        implements
            ITokenLexer<List<String>> {

    ITokenLexer<String> tokenLexer;

    public SpaceSeparatedLexer(ITokenLexer<String> tokenLexer) {
        if (tokenLexer == null)
            throw new NullPointerException("tokenLexer");
        this.tokenLexer = tokenLexer;
    }

    @Override
    public List<String> lex(ILa1CharIn in) throws IOException, ParseException {
        List<String> list = new ArrayList<>();
        int c;
        while ((c = in.look()) != -1) {
            if (Character.isWhitespace(c)) {
                in.read();
                continue;
            }
            String token = tokenLexer.lex(in);
            list.add(token);
        }
        return list;
    }

    public static SpaceSeparatedLexer SS_QUOTED = new SpaceSeparatedLexer(QuotableTokenLexer.AS_IS);
    public static SpaceSeparatedLexer SS_DEQUOTED = new SpaceSeparatedLexer(QuotableTokenLexer.DEQUOTED);

}
