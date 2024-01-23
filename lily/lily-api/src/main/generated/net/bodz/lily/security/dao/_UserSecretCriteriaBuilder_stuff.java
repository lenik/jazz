package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _UserSecretCriteriaBuilder_stuff<self_t extends _UserSecretCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** The declaring user */
    public final IntegerField userId = integer("\"user\"");

    /** Password data */
    public final StringField password = string("passwd");

    /** Protection question */
    public final StringField question = string("question");

    /** Protection answer */
    public final StringField answer = string("answer");

}
