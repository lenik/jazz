package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.ArticleTagType;

@ForEntityType(ArticleTagType.class)
public class _ArticleTagTypeCriteriaBuilder_stuff<self_t extends _ArticleTagTypeCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
