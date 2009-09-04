package net.bodz.swt.gui.pfl;

import net.bodz.swt.gui.QuietHint;

public class PageException extends Exception implements QuietHint {

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
