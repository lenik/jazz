package net.bodz.lily.concrete;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;

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
