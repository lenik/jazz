package net.bodz.violet.art.dao;

import net.bodz.lily.template.CoCategoryMask;

/**
 * @see net.bodz.violet.art.ArtifactCategory
 */
public class ArtifactCategoryMask
        extends CoCategoryMask {

    public static ArtifactCategoryMask below(int maxDepth) {
        ArtifactCategoryMask mask = new ArtifactCategoryMask();
        mask.maxDepth = 1;
        return mask;
    }

}
