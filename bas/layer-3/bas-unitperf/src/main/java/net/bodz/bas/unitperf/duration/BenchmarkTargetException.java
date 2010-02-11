package net.bodz.bas.unitperf.duration;

public class BenchmarkTargetException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public BenchmarkTargetException() {
        super();
    }

    public BenchmarkTargetException(String message, Throwable cause) {
        super(message, cause);
    }

    public BenchmarkTargetException(String message) {
        super(message);
    }

    public BenchmarkTargetException(Throwable cause) {
        super(cause);
    }

}
