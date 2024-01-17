package net.bodz.violet.art.dao;

import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;

public class _ArtifactModelCriteriaBuilder_stuff<self_t extends _ArtifactModelCriteriaBuilder_stuff<self_t>>
        extends CoMomentIntervalCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final BooleanField valid = bool("\"valid\"");

    public final IntegerField obsoleteId = integer("obsolete");

    public final IntegerField artifactId = integer("art");

    public final StringField model = string("model");

}
