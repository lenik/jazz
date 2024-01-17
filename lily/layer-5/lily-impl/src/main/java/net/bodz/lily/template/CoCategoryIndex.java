package net.bodz.lily.template;

import net.bodz.lily.model.base.CoIndex;

public abstract class CoCategoryIndex<T extends CoCategory<T, ?>, M extends CoCategoryCriteriaBuilder>
        extends CoIndex<T, M> {

    public CoCategoryIndex() {
        super();
    }

}
