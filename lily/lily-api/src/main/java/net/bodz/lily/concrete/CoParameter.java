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
public abstract class CoParameter<self_t extends CoParameter<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    public CoParameter() {
        super();
    }

    public CoParameter(self_t parent) {
        super(parent);
    }

}
