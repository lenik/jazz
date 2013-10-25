package net.bodz.pkg.sis;

import java.util.List;

import net.bodz.bas.t.IDependencyManager;

public interface ISisComponentManager
        extends IDependencyManager<ISisComponent> {

    ISisComponent getComponent(String id);

    ISisComponent getFollowing(ISisComponent node);

    // ISisComponent getPreceding(ISisComponent node);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    List<ISisComponent> getMissingDependencies(Iterable<? extends ISisComponent> components);

}
