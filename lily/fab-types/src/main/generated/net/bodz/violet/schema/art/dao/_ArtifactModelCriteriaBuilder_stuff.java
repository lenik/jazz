package net.bodz.violet.schema.art.dao;

import java.time.OffsetDateTime;

import net.bodz.lily.concrete.CoImagedEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactModel;

@ForEntityType(ArtifactModel.class)
public class _ArtifactModelCriteriaBuilder_stuff<self_t extends _ArtifactModelCriteriaBuilder_stuff<self_t>>
        extends CoImagedEventCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final BooleanField valid = bool("\"valid\"");

    public final DateField<OffsetDateTime> validSince = date("validsince", OffsetDateTime.class);

    public final DateField<OffsetDateTime> validUntil = date("validuntil", OffsetDateTime.class);

    public final IntegerField obsoleteId = integer("obsolete");

    public final IntegerField artifactId = integer("art");

    public final StringField modelName = string("model");

}
