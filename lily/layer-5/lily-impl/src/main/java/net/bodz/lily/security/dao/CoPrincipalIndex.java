package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.CoPrincipal;

public abstract class CoPrincipalIndex<T extends CoPrincipal, M extends CoPrincipalCriteriaBuilder>
        extends CoIndex<T, M> {

    String schema;

    public CoPrincipalIndex(String schema) {
        this.schema = schema;
    }

}
