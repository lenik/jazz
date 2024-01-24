package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _ArtifactBackrefCriteriaBuilder_stuff<self_t extends _ArtifactBackrefCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final IntegerField siteId = integer("site");

    public final StringField key = string("\"key\"");

    public final IntegerField value = integer("\"value\"");

}
