package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactType;

@ForEntityType(ArtifactType.class)
public class _ArtifactTypeCriteriaBuilder_stuff<self_t extends _ArtifactTypeCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField parentId = integer("parent");

    public final IntegerField uomId = integer("uom");

}
