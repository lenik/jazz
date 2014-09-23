package net.bodz.lily.model.base.schema;

import net.bodz.lily.model.base.CoCode;

public abstract class AbstractDefinition<self_t extends AbstractDefinition<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    public AbstractDefinition() {
        super();
    }

    public AbstractDefinition(self_t parent) {
        super(parent);
    }

}
