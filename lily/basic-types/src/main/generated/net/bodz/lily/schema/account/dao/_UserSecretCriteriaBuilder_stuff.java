package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.account.UserSecret;

@ForEntityType(UserSecret.class)
public class _UserSecretCriteriaBuilder_stuff<self_t extends _UserSecretCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** The declaring user */
    public final IntegerField userId = integer("\"user\"");

    /** Password data */
    public final StringField password = string("passwd");

    /** Recover Question */
    public final StringField question = string("question");

    /** Recover Answer */
    public final StringField answer = string("answer");

}
