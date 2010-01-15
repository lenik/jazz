package net.bodz.bas.text.lop.impl;

import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.reflect.bind.ReflectBind;
import net.bodz.bas.reflect.bind.ReflectField;
import net.bodz.bas.reflect.bind.ReflectMethod;
import net.bodz.bas.text.lop._Lexer;
import net.bodz.bas.text.lop.util.XYTellable;

/**
 * Subclass must implement following members: <code>
 * <ul>
 * <li>int yyline;
 * <li>int yycolumn;
 * <li>Reader zzReader;
 * <li>int zzCurrentPos;
 * <li>boolean zzAtEOF;
 * <li>int yylex();
 * <li>void yyreset(Reader);
 * <li>void yyclose();
 * <li>int yystate();
 * <li>void yybegin(int);
 * <li>String yytext();
 * <li>char yycharat(int);
 * <li>int yylength();
 * </ul>
 * </code>
 * 
 * @since JFlex 1.4.1
 */
public abstract class JFlexLexer
        extends _Lexer
        implements XYTellable {

    @ReflectMethod("yylex")
    private Method yylex; // public int()

    @ReflectMethod(value = "yyreset", parameters = Reader.class)
    private Method yyreset; // public void(Reader)

    @ReflectMethod("yyclose")
    private Method yyclose; // public void()

    @ReflectMethod("yystate")
    private Method yystate; // public int()

    @ReflectMethod(value = "yybegin", parameters = int.class)
    private Method yybegin; // public void(int)

    @ReflectMethod("yytext")
    private Method yytext; // public String()

    @ReflectMethod(value = "yycharat", parameters = int.class)
    private Method yycharat; // public char(int)

    @ReflectMethod("yylength")
    private Method yylength; // public int()

    @ReflectField("yyline")
    private Field yyline; // private int

    @ReflectField("yycolumn")
    private Field yycolumn; // private int

    @ReflectField("zzReader")
    private Field zzReader; // private Reader

    @ReflectField("zzCurrentPos")
    private Field zzCurrentPos; // private int

    @ReflectField("zzAtEOF")
    private Field zzAtEOF; // private boolean, see also zzEOFDone

    /**
     * JFlex constructors: <code><ul>  
     * <li>ctor(Reader)
     * <li>ctor(InputStream)
     * </ul></code>
     */

    protected JFlexLexer() {
        ReflectBind.bind(JFlexLexer.class, this);
    }

    protected JFlexLexer(Reader in) {
        this();
        // set zzReader=in is slightly faster then reset(in).
        ReflectBind.set(this, zzReader, in);
    }

    @Override
    public void reset(Reader in) {
        ReflectBind.invoke(this, yyreset, in);
    }

    @Override
    public void close() {
        ReflectBind.invoke(this, yyclose);
    }

    @Override
    public long tell() {
        return ReflectBind.getInt(this, zzCurrentPos);
    }

    @Override
    public int tellY() {
        int lines = ReflectBind.getInt(this, yyline);
        return lines;
    }

    @Override
    public int tellX() {
        int columns = (Integer) ReflectBind.get(this, yycolumn);
        return columns;
    }

    @Override
    public String getText() {
        return (String) ReflectBind.invoke(this, yytext);
    }

    @Override
    public int getLength() {
        return (Integer) ReflectBind.invoke(this, yylength);
    }

    @Override
    public char getChar(int index) {
        return (Character) ReflectBind.invoke(this, yycharat, index);
    }

    @Override
    protected void setState(int state) {
        ReflectBind.invoke(this, yybegin, state);
    }

    @Override
    public int state() {
        int state = (Integer) ReflectBind.invoke(this, yystate);
        return state;
    }

    /**
     * Though JFlex allow yylex() returns Object, but we don't use this feature, instead, we return
     * Object by call {@link #setValue(Object)}.
     */
    @Override
    protected int _read() {
        int c = (Integer) ReflectBind.invoke(this, yylex);
        if (c == 0) {
            if (ReflectBind.getBoolean(this, zzAtEOF))
                return -1;
        }
        return c;
    }

    @Override
    protected XYTellable getTokenStart() {
        return this;
    }

}
