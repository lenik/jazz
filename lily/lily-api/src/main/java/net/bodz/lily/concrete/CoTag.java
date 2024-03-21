package net.bodz.lily.concrete;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.TsTyped;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@FieldGroupVue
@IdType(Integer.class)
@TsTyped
@TypeParameters({ TypeParamType.THIS_REC })
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
