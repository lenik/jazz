package net.bodz.bas.log.objects;

public class DelayedFormat
        extends AbstractLogEvent {

    private final String format;
    private Object[] args;

    private String result;

    public DelayedFormat(String format, Object[] args) {
        if (format == null)
            throw new NullPointerException("format");
        this.format = format;
        this.args = args;
    }

    @Override
    public String toString() {
        if (result == null) {
            result = String.format(format, args);
            args = null;
        }
        return result;
    }

}
