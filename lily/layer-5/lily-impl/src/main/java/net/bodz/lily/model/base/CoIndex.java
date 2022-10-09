package net.bodz.lily.model.base;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.lily.security.AccessControl;

@AccessControl
@IndexedType(includeAbstract = false)
@VirtualHostScope
public abstract class CoIndex<T extends CoObject, M extends CoObjectMask>
        extends AbstractEntityManager<T, M> {

    static final Logger logger = LoggerFactory.getLogger(CoIndex.class);

}
