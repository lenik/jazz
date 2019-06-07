package net.bodz.lily.security.impl;

import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.CoPrincipal;

public abstract class CoPrincipalIndex<T extends CoPrincipal, M extends CoPrincipalMask>
        extends CoIndex<T, M> {

    private String schema;

    public CoPrincipalIndex(String schema) {
        this.schema = schema;
    }

}
