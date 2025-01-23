package net.bodz.violet.schema.store.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoNodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.store.Region;

@ForEntityType(Region.class)
public class _RegionCriteriaBuilder_stuff<self_t extends _RegionCriteriaBuilder_stuff<self_t>>
        extends CoNodeCriteriaBuilder<self_t> {

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final StringField path = string("\"path\"");

    public final IntegerField protoId = integer("proto");

    public final IntegerField height = integer("height");

    public final IntegerField levelId = integer("\"level\"");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField tagId = integer("tag");

    public final IntegerField materialId = integer("material");

    public final IntegerField forCatId = integer("for_cat");

    public final IntegerField forArtId = integer("for_art");

    public final IntegerField artifactId = integer("art");

}
