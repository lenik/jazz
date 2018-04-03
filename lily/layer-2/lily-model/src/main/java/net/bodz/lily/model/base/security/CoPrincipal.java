package net.bodz.lily.model.base.security;

import net.bodz.lily.model.base.CoEntity;

public class CoPrincipal
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    CoPrincipalProperties properties;

    @Override
    public CoPrincipalProperties getProperties() {
        return properties;
    }

}
