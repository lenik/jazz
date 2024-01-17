package net.bodz.lily.model.base;

public class CoCodeCriteriaBuilder<self_t extends CoCodeCriteriaBuilder<self_t>>
        extends CoNodeCriteriaBuilder<self_t> {

    public final StringField code = string("code");

}
