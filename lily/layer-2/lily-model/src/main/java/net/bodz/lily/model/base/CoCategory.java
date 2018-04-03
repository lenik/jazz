package net.bodz.lily.model.base;


public abstract class CoCategory<self_t extends CoCategory<self_t, Id>, Id>
        extends CoNode<self_t, Id> {

    private static final long serialVersionUID = 1L;

    CoCategoryProperties properties = new CoCategoryProperties();;

    public CoCategory() {
        super();
    }

    public CoCategory(self_t parent) {
        super(parent);
    }

    @Override
    public CoCategoryProperties getProperties() {
        return properties;
    }

}
