package net.bodz.lily.template;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;

@IdType(Integer.class)
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
