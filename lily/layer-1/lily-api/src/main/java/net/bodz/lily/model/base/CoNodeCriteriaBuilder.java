package net.bodz.lily.model.base;

public class CoNodeCriteriaBuilder<self_t extends CoNodeCriteriaBuilder<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField parentId = _long("parent");
    public final IntegerField depth = integer("depth");
    public final IntegerField id = integer("id");

}
