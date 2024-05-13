package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactParam;

@ForEntityType(ArtifactParam.class)
public class _ArtifactParamCriteriaBuilder_stuff<self_t extends _ArtifactParamCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField artifactId = integer("art");

    public final IntegerField parameterId = integer("parm");

    public final IntegerField ival = integer("ival");

    public final DoubleField fval = _double("fval");

    public final StringField sval = string("sval");

}
