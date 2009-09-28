package net.bodz.mda.tmpl.xtx;

import java.io.IOException;
import java.util.Date;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.types.util.Strings;

public abstract class _CodeEmitter implements CodeEmitter {

    private boolean         multiline;
    private int             heredocMinSize = 100;

    private String          source         = "* unknown *";
    private int             line;
    private int             column;

    protected final CharOut out;

    public _CodeEmitter(CharOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    @Override
    public void setSourceLocation(String source) {
        this.source = source;
    }

    @Override
    public void setSourcePosition(int line, int column) {
        this.line = line;
        this.column = column;
    }

    protected String getSource() {
        return source;
    }

    protected int getLine() {
        return line;
    }

    protected int getColumn() {
        return column;
    }

    @Override
    public void start() {
        emitCommentLine("");
        emitCommentLine("Emitted by: " + getClass().getName());
        emitCommentLine("Source: " + getSource());
        emitCommentLine("");
    }

    @Override
    public void end() {
        emitCommentLine("");
        emitCommentLine("Emit date: " + new Date());
        emitCommentLine("");
    }

    protected abstract String emitCommentLine(String s);

    @Override
    public void emitCode(String code) throws IOException {
        out.print(code);
    }

    @Override
    public final void emitExpr(String exprCode) throws IOException {
        emitPrintExpr(exprCode, false);
    }

    @Override
    public final void emitText(String text) throws IOException {
        if (multiline) {
            int i;
            while ((i = text.indexOf('\n')) != -1) {
                String line = text.substring(0, i);
                text = text.substring(i + 1);
                emitPrintExpr(quote(line), true);
            }
        } else if (text.length() >= heredocMinSize) {
            String heredoc = quoteHeredoc(text);
            if (heredoc != null) {
                emitHereDoc(heredoc);
                return;
            }
        }
        emitPrintExpr(text, false);
    }

    public void emitHereDoc(String doc) throws IOException {
        throw new UnsupportedOperationException("heredoc isn't supported. ");
    }

    public abstract void emitPrintExpr(String exprCode, boolean newLine) throws IOException;

    protected String quote(String s) {
        String escaped = Strings.escape(s);
        String quoted = '"' + escaped + '"';
        return quoted;
    }

    /**
     * @return <code>null</code> if heredoc isn't supported, or the given string can't be converted
     *         to heredoc.
     */
    protected String quoteHeredoc(String s) {
        return s;
    }

}
