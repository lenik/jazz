package net.bodz.bas.text.lop;

public abstract class AbstractParser implements Parser {

    protected Lexer lexer;

    public AbstractParser() {
    }

    protected Token read() {
        Token token = lexer.read();
        setValue(token.getValue());
        return token;
    }

    @Override
    public int state() {
        return state(0);
    }

}
