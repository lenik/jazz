package net.bodz.violet.art.impl;

import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.art.ArtifactCategory
 */
public class ArtifactCategoryMask
        extends CoNodeMask {

    public static ArtifactCategoryMask below(int maxDepth) {
        ArtifactCategoryMask mask = new ArtifactCategoryMask();
        mask.maxDepth = 1;
        return mask;
    }

}
