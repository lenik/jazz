package net.bodz.bas.tool.make;

import java.util.Collection;

public interface IMakeTarget {

    long getVersion();

    String getName();

    Collection<? extends IMakeTarget> getDependencies();

    boolean isUpdated();

    /**
     * Always make this target.
     */
    void make()
            throws Exception;

    /**
     * Make this target when it's expired.
     * <p>
     * This is just the same as: isUpdated() || make().
     * </p>
     */
    void update()
            throws Exception;

}
