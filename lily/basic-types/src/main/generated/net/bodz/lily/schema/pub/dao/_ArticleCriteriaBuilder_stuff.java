package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.pub.Article;

@ForEntityType(Article.class)
public class _ArticleCriteriaBuilder_stuff<self_t extends _ArticleCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField favCount = integer("nfav");

    public final IntegerField hateCount = integer("nhate");

    public final IntegerField messageCount = integer("nmsg");

    public final DiscreteField<JsonVariant> plugins = discrete("plugins", JsonVariant.class);

}
