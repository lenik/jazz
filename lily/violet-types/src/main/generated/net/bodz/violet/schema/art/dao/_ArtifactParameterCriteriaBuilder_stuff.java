package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactParameter;

@ForEntityType(ArtifactParameter.class)
public class _ArtifactParameterCriteriaBuilder_stuff<self_t extends _ArtifactParameterCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField name = string("\"name\"");

    public final IntegerField dummy = integer("dummy");

}
