package net.bodz.violet.art.dao;

import net.bodz.lily.template.CoCategoryCriteriaBuilder;

/**
 * @see net.bodz.violet.art.ArtifactCategory
 */
public class ArtifactCategoryCriteriaBuilder
        extends CoCategoryCriteriaBuilder {

    public static ArtifactCategoryCriteriaBuilder below(int maxDepth) {
        ArtifactCategoryCriteriaBuilder mask = new ArtifactCategoryCriteriaBuilder();
        mask.maxDepth = 1;
        return mask;
    }

}
