package net.bodz.lily.model.base;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.variant.IVarMapForm;

public abstract class CoIndex<T, M extends IVarMapForm>
        extends AbstractEntityManager<T, M> {

    static final Logger logger = LoggerFactory.getLogger(CoIndex.class);

    public CoIndex() {
        super();
    }

}
