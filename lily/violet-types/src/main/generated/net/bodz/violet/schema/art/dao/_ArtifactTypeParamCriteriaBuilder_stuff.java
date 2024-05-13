package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactTypeParam;

@ForEntityType(ArtifactTypeParam.class)
public class _ArtifactTypeParamCriteriaBuilder_stuff<self_t extends _ArtifactTypeParamCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField arttypeId = integer("arttype");

    public final IntegerField parameterId = integer("parm");

    public final IntegerField ival = integer("ival");

    public final DoubleField fval = _double("fval");

    public final StringField sval = string("sval");

}
