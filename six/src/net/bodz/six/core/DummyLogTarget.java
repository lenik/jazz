package net.bodz.six.core;

public class DummyLogTarget implements LogTarget {

    public void log(String message) {
    }

    public static final LogTarget DUMMY = new DummyLogTarget();

}
