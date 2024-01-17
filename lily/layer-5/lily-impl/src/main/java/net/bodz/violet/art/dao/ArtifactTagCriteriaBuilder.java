package net.bodz.violet.art.dao;

import net.bodz.lily.model.base.CoNodeCriteriaBuilder;

/**
 * @see net.bodz.violet.art.ArtifactCategory
 */
public class ArtifactTagCriteriaBuilder
        extends CoNodeCriteriaBuilder {

    public static ArtifactTagCriteriaBuilder below(int maxDepth) {
        ArtifactTagCriteriaBuilder mask = new ArtifactTagCriteriaBuilder();
        mask.maxDepth = 1;
        return mask;
    }

}
