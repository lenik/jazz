package net.bodz.lily.concrete;

public class CoNodeCriteriaBuilder<self_t extends CoNodeCriteriaBuilder<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField parentId = _long("parent");
    public final IntegerField depth = integer("depth");
    public final IntegerField id = integer("id");

}
