package net.bodz.bas.mod.plugins;

public class PluginException extends Exception {

    private static final long serialVersionUID = -6582892734183870538L;

    public PluginException() {
        super();
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(Throwable cause) {
        super(cause);
    }

}
