package net.bodz.violet.art.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.art.ArtifactParameter;

/**
 * 物料参数
 */
@ObjectType(ArtifactParameter.class)
public class ArtifactParameterIndex
        extends CoIndex<ArtifactParameter, ArtifactParameterCriteriaBuilder> {

    public ArtifactParameterIndex() {
    }

}
