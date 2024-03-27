package net.bodz.violet.schema.fab.dao;

import java.time.OffsetDateTime;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _FabStdProcessCriteriaBuilder_stuff<self_t extends _FabStdProcessCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final BooleanField valid = bool("\"valid\"");

    public final DateField<OffsetDateTime> validSince = date("validsince", OffsetDateTime.class);

    public final DateField<OffsetDateTime> validUntil = date("validuntil", OffsetDateTime.class);

    public final IntegerField outputId = integer("\"output\"");

    public final IntegerField functionId = integer("fn");

    public final IntegerField duration = integer("duration");

    public final BooleanField strict = bool("strict");

    public final IntegerField testId = integer("test");

}
