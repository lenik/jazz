package net.bodz.violet.art.dao;

import net.bodz.lily.model.base.CoNodeCriteriaBuilder;

/**
 * @see net.bodz.violet.art.ArtifactParameter
 */
public class ArtifactParameterCriteriaBuilder
        extends CoNodeCriteriaBuilder {

    public static ArtifactParameterCriteriaBuilder below(int maxDepth) {
        ArtifactParameterCriteriaBuilder mask = new ArtifactParameterCriteriaBuilder();
        mask.maxDepth = 1;
        return mask;
    }

}
