package net.bodz.mda.parsers;

import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.lang.annotations.ReflectField;
import net.bodz.bas.lang.annotations.ReflectMethod;
import net.bodz.bas.lang.util.Reflects;

/**
 * @since JFlex 1.4.1
 */
public abstract class JFlexLexer extends _Lexer {

    @ReflectField
    private Field  YYINITIAL;

    @ReflectMethod
    private Method yylex;

    @ReflectMethod(value = "yyreset", parameters = Reader.class)
    private Method yyreset;

    @ReflectMethod
    private Method yyclose;

    @ReflectMethod
    private Method yystate;

    @ReflectMethod(value = "yybegin", parameters = int.class)
    private Method yybegin;

    @ReflectMethod
    private Method yytext;

    @ReflectMethod(value = "yycharat", parameters = int.class)
    private Method yycharat;

    @ReflectMethod
    private Method yylength;

    @ReflectField
    private Field  yychar;

    @ReflectField
    private Field  yyline;

    @ReflectField
    private Field  yycolumn;

    protected JFlexLexer() {
        this(null);
    }

    protected JFlexLexer(Parser parser) {
        super(parser);
        Reflects.bind(JFlexLexer.class, this);
    }

    @Override
    public void reset(Reader in) {
        Reflects.invoke(this, yyreset, in);
    }

    @Override
    public void close() {
        Reflects.invoke(this, yyclose);
    }

    @Override
    public long tell() {
        return (Long) Reflects.get(this, yychar);
    }

    @Override
    public int line() {
        return (Integer) Reflects.get(this, yyline);
    }

    @Override
    public int column() {
        return (Integer) Reflects.get(this, yycolumn);
    }

    @Override
    public String getText() {
        return (String) Reflects.invoke(this, yytext);
    }

    @Override
    public int getLength() {
        return (Integer) Reflects.invoke(this, yylength);
    }

    @Override
    public char getChar(int index) {
        return (Character) Reflects.invoke(this, yycharat, index);
    }

    @Override
    protected int getInitialState() {
        return (Integer) Reflects.get(null, YYINITIAL);
    }

    @Override
    protected void setState(int state) {
        Reflects.invoke(this, yybegin, state);
    }

    @Override
    public int state() {
        int internalState = super.state();
        int state = (Integer) Reflects.invoke(this, yystate);
        if (internalState != state)
            throw new IllegalStateException( //
                    "yystate=" + state + ", internal=" + internalState);
        return state;
    }

    /**
     * Though JFlex allow yylex() returns Object, but we don't use this feature,
     * instead, we return Object by call {@link #setValue(Object)}.
     */
    @Override
    protected int _read() {
        return (Integer) Reflects.invoke(this, yylex);
    }

}
