package net.bodz.bas.text.lop;

import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.util.IXYTellable;
import net.bodz.bas.sio.util.XYPosition;
import net.bodz.bas.util.string.StringEscape;
import net.bodz.bas.util.string.Strings;

public abstract class AbstractToken
        implements Token {

    private final Lexer lexer;
    private final int id;
    private final Object value;

    private long offset;
    private int y;
    private int x;

    public AbstractToken(Lexer lexer, int id, IXYTellable start) {
        this(lexer, id, null, start);
    }

    public AbstractToken(Lexer lexer, int id, Object value, IXYTellable start) {
        if (lexer == null)
            throw new NullPointerException("lexer");
        this.lexer = lexer;
        this.id = id;
        this.value = value;
        this.offset = start.tell();
        this.y = start.tellY();
        this.x = start.tellX();
    }

    public AbstractToken(Lexer lexer, int id, long offset, int y, int x) {
        this(lexer, id, null, offset, y, x);
    }

    public AbstractToken(Lexer lexer, int id, Object value, long offset, int y, int x) {
        if (lexer == null)
            throw new NullPointerException("lexer");
        this.lexer = lexer;
        this.id = id;
        this.value = value;
        this.offset = offset;
        this.y = y;
        this.x = x;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return lexer.getTokenName(id);
    }

    @Override
    public char charAt(int index) {
        return getText().charAt(index);
    }

    @Override
    public int length() {
        String text = getText();
        assert text != null;
        return text.length();
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public long tell() {
        return offset;
    }

    @Override
    public int tellX() {
        return x;
    }

    @Override
    public int tellY() {
        return y;
    }

    @Override
    public IXYTellable end() {
        String text = getText();
        XYPosition end = new XYPosition(this).add(text);
        return end;
    }

    /**
     * token N(Name)="text" at offset(line:column), value=?
     */
    @Override
    public String toString() {
        int id = getId();
        String name = getName();
        Object value = getValue();
        String text = getText();

        BCharOut out = new BCharOut();

        out.print(XYPosition.format(this));
        out.print(": ");

        if (name != null)
            out.print(name);
        else
            out.print(id);

        if (text != null) {
            if (text.length() == 1 && text.charAt(0) == id)
                ; // possible (char)ID
            else {
                String textBrief = Strings.ellipse(text, 20);
                out.print("(\"");
                out.print(StringEscape.java(textBrief));
                out.print("\")");
            }
        }

        if (value != null) {
            out.print(" => ");
            out.print(String.valueOf(value));
        }

        return out.toString();
    }

}
