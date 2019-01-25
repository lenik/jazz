package net.bodz.lily.template;

import net.bodz.lily.model.base.CoNode;

public abstract class CoCategory<self_t extends CoCategory<self_t, Id>, Id>
        extends CoNode<self_t, Id> {

    private static final long serialVersionUID = 1L;

    RichProperties properties = new RichProperties();;

    public CoCategory() {
        super();
    }

    public CoCategory(self_t parent) {
        super(parent);
    }

    @Override
    public RichProperties getProperties() {
        return properties;
    }

}
