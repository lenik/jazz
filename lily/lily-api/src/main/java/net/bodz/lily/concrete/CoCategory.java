package net.bodz.lily.concrete;

import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.meta.CriteriaClass;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@CriteriaClass(CoCategoryCriteriaBuilder.class)
@TypeParameters({ TypeParamType.THIS_REC, TypeParamType.ID_TYPE })
@HaveAttachments
public abstract class CoCategory<self_t extends CoCategory<self_t, Id>, Id>
        extends CoNode<self_t, Id> {

    private static final long serialVersionUID = 1L;

    public CoCategory() {
        super();
    }

    public CoCategory(self_t parent) {
        super(parent);
    }

}
