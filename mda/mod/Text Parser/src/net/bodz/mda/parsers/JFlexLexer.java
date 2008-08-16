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
    private Field  YYINITIAL;   // public int

    @ReflectMethod
    private Method yylex;       // public int()

    @ReflectMethod(value = "yyreset", parameters = Reader.class)
    private Method yyreset;     // public void(Reader)

    @ReflectMethod
    private Method yyclose;     // public void()

    @ReflectMethod
    private Method yystate;     // public int()

    @ReflectMethod(value = "yybegin", parameters = int.class)
    private Method yybegin;     // public void(int)

    @ReflectMethod
    private Method yytext;      // public String()

    @ReflectMethod(value = "yycharat", parameters = int.class)
    private Method yycharat;    // public char(int)

    @ReflectMethod
    private Method yylength;    // public int()

    @ReflectField
    private Field  yyline;      // private int

    @ReflectField
    private Field  yycolumn;    // private int

    @ReflectField
    private Field  zzReader;    // private Reader

    @ReflectField
    private Field  zzCurrentPos; // private int

    @ReflectField
    private Field  zzAtEOF;     // private boolean, see also zzEOFDone

    protected JFlexLexer(Reader in) {
        super(false);
        Reflects.bind(JFlexLexer.class, this);
        init();
        // set zzReader=in is slightly faster then reset(in).
        Reflects.set(this, zzReader, in);
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
        return (Integer) Reflects.get(this, zzCurrentPos);
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
        int c = (Integer) Reflects.invoke(this, yylex);
        if (c == 0) {
            if ((Boolean) Reflects.get(this, zzAtEOF))
                return -1;
        }
        return c;
    }

}
