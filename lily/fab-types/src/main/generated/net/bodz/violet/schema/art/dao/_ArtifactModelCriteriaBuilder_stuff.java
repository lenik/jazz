package net.bodz.violet.schema.art.dao;

import java.sql.Timestamp;

import net.bodz.lily.concrete.CoEventCriteriaBuilder;

public class _ArtifactModelCriteriaBuilder_stuff<self_t extends _ArtifactModelCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final BooleanField valid = bool("\"valid\"");

    public final DateField<Timestamp> validSince = date("validsince", Timestamp.class);

    public final DateField<Timestamp> validUntil = date("validuntil", Timestamp.class);

    public final IntegerField obsoleteId = integer("obsolete");

    public final IntegerField artifactId = integer("art");

    public final StringField modelName = string("model");

}
