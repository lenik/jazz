package net.bodz.dist.ins;

public class PackageCorruptedException extends Exception {

    private static final long serialVersionUID = 7213668549695593545L;

    public PackageCorruptedException() {
        super();
    }

    public PackageCorruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageCorruptedException(String message) {
        super(message);
    }

    public PackageCorruptedException(Throwable cause) {
        super(cause);
    }

}
