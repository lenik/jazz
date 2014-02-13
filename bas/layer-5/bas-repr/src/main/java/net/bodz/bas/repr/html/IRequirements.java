package net.bodz.bas.repr.html;

import java.util.Collection;

public interface IRequirements {

    IRequirement get(String id);

    Collection<IRequirement> typeOf(String type);

    void add(String type, String id, String minVersion)
            throws ConflictedVersionException;

    void add(IRequirement requirement)
            throws ConflictedVersionException;

    /**
     * @return <code>true</code> if specific requirement is existed and removed.
     */
    boolean remove(String id);

}
