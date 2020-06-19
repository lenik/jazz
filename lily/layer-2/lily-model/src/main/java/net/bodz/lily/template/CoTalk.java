package net.bodz.lily.template;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
public abstract class CoTalk<self_t>
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    self_t parent;

    public final self_t getParent() {
        return parent;
    }

    public void setParent(self_t parent) {
        this.parent = parent;
    }

}
