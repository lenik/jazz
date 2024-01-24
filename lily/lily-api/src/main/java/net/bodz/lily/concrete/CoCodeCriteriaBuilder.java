package net.bodz.lily.concrete;

public class CoCodeCriteriaBuilder<self_t extends CoCodeCriteriaBuilder<self_t>>
        extends CoNodeCriteriaBuilder<self_t> {

    public final StringField code = string("code");

}
