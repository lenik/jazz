package net.bodz.bas.db.stat;

import java.util.Collection;

/**
 * A counter specification contains multiple definition.
 */
public interface ICounterSpec {

    Collection<ICounterDef<?>> getDefinitions();

}
