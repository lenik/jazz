package net.bodz.bas.fs;

public class RelativeFileContainer
        extends AbstractFileContainer {

    private static RelativeFileContainer instance = new RelativeFileContainer();

    public static RelativeFileContainer getInstance() {
        return instance;
    }

}
