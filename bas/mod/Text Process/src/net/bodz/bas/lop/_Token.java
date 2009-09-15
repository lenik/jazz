package net.bodz.bas.lop;

import java.io.IOException;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lop.util.XYPosition;
import net.bodz.bas.lop.util.XYTellable;
import net.bodz.bas.types.util.Strings;

public abstract class _Token implements Token {

    private final Lexer  lexer;
    private final int    id;
    private final Object value;

    public _Token(Lexer lexer, int id) {
        this(lexer, id, null);
    }

    public _Token(Lexer lexer, int id, Object value) {
        if (lexer == null)
            throw new NullPointerException("lexer");
        this.lexer = lexer;
        this.id = id;
        this.value = value;
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
    public long tell() throws IOException {
        return lexer.tell();
    }

    @Override
    public int tellX() {
        return lexer.tellX();
    }

    @Override
    public int tellY() {
        return lexer.tellY();
    }

    @Override
    public XYTellable getBoundary() {
        long offset = -1;
        try {
            offset = lexer.tell();
        } catch (IOException e) {
        }
        int x = lexer.tellX();
        int y = lexer.tellY();
        XYPosition boundary = new XYPosition(offset, y, x);
        return boundary;
    }

    /**
     * token N(Name)="text" at offset(line:column), value=?
     */
    @Override
    public String toString() {
        int id = getId();
        String name = getName();
        long start = -1;
        try {
            start = tell();
        } catch (IOException e) {
        }
        int line = tellY() + 1;
        int column = tellX() + 1;
        Object value = getValue();
        String text = getText();

        BCharOut out = new BCharOut();

        out.print(line);
        out.print(":");
        out.print(column);
        if (start != -1) {
            out.print("+");
            out.print(start);
        }
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
                out.print(Strings.escape(textBrief));
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
