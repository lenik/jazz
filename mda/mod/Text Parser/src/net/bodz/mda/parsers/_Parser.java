package net.bodz.mda.parsers;

public abstract class _Parser implements Parser {

    protected Lexer lexer;

    public _Parser() {
    }

    protected Token read() {
        Token token = lexer.read();
        setValue(token.value());
        return token;
    }

    @Override
    public int state() {
        return state(0);
    }

}
