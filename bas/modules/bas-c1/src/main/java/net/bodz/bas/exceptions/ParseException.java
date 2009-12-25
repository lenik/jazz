package net.bodz.bas.exceptions;

import net.bodz.bas.module.ModuleInfo;
import net.bodz.bas.nls_2.NLS;

public class ParseException
        extends Exception {

    private static final long serialVersionUID = 7260313523533602858L;

    public String source;
    public int line;
    public int column;

    public ParseException() {
        super();
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(Class<?> unparsableType, String unparsableText) {
        this(NLS.format("unknownParse: %s", unparsableType));
    }

    public void setLocation(String source) {
        setLocation(source, 0, 0);
    }

    public void setLocation(String source, int line) {
        setLocation(source, line, 0);
    }

    public void setLocation(String source, int line, int column) {
        this.source = source;
        this.line = line;
        this.column = column;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * "Line 23: at column 45, error message"
     */
    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (source == null && line == 0 && column == 0)
            return message;
        StringBuffer buf = new StringBuffer(message.length());
        boolean prefixed = false;
        if (source != null) {
            buf.append(source);
            buf.append(":");
            prefixed = true;
        }
        if (line != 0) {
            if (source == null)
                buf.append("Line ");
            buf.append(line);
            buf.append(':');
            if (column != 0) {
                buf.append(" at column ");
                buf.append(column);
                buf.append(',');
            }
            prefixed = true;
        }
        if (prefixed)
            buf.append(' ');
        return buf.toString();
    }

    private static NLS NLS = ModuleInfo.getInstance().getNLS();

}
