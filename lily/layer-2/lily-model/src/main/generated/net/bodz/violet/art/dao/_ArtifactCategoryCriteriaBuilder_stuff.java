package net.bodz.violet.art.dao;

import net.bodz.lily.template.CoCategoryCriteriaBuilder;

public class _ArtifactCategoryCriteriaBuilder_stuff<self_t extends _ArtifactCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField skufmt = string("skufmt");

    public final StringField skufmtfull = string("skufmtfull");

    public final StringField barfmt = string("barfmt");

    public final StringField barfmtfull = string("barfmtfull");

    public final StringField batchfmt = string("batchfmt");

    public final StringField serialfmt = string("serialfmt");

    public final IntegerField count = integer("\"count\"");

}
