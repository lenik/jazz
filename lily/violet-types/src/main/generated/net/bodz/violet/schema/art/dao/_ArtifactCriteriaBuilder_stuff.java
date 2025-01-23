package net.bodz.violet.schema.art.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.Artifact;

@ForEntityType(Artifact.class)
public class _ArtifactCriteriaBuilder_stuff<self_t extends _ArtifactCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField skuCode = string("sku");

    public final StringField barCode = string("barcode");

    public final StringField rfidCode = string("rfid");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField modelName = string("model");

    public final IntegerField protoId = integer("proto");

    public final IntegerField typeId = integer("\"type\"");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField phaseId = integer("phase");

    public final IntegerField uomId = integer("uom");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final ShortField finish = _short("finish");

    public final BigDecimalField price = bigDecimal("price");

}
