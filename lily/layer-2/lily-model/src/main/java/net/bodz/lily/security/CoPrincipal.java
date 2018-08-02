package net.bodz.lily.security;

import net.bodz.lily.model.base.CoEntity;

public abstract class CoPrincipal
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    CoPrincipalProperties properties;

    @Override
    public CoPrincipalProperties getProperties() {
        return properties;
    }

}
