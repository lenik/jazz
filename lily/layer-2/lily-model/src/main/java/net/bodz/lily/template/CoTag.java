package net.bodz.lily.template;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.model.base.CoCode;

@TypeParameters({ TypeParamType.THIS_REC })
@IdType(Integer.class)
public abstract class CoTag<self_t extends CoTag<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    public CoTag() {
        super();
    }

    public CoTag(self_t parent) {
        super(parent);
    }

}
