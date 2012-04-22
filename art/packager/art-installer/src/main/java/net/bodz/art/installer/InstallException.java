package net.bodz.art.installer;

public class InstallException extends Exception {

    private static final long serialVersionUID = 5056999498893599794L;

    public InstallException() {
        super();
    }

    public InstallException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstallException(String message) {
        super(message);
    }

    public InstallException(Throwable cause) {
        super(cause);
    }

}
