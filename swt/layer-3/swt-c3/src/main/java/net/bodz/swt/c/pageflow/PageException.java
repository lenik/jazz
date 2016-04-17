package net.bodz.swt.c.pageflow;

import net.bodz.bas.ui.event.IQuietHint;

public class PageException
        extends Exception
        implements IQuietHint {

    private static final long serialVersionUID = -2027672146891361147L;

    public PageException() {
        super();
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageException(String message) {
        super(message);
    }

    public PageException(Throwable cause) {
        super(cause);
    }

    @Override
    public boolean isQuiet() {
        return false;
    }

}
