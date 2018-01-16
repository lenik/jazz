package net.bodz.violet.art.impl;

import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.art.ArtifactCategory
 */
public class ArtifactTagMask
        extends CoNodeMask {

    public static ArtifactTagMask below(int maxDepth) {
        ArtifactTagMask mask = new ArtifactTagMask();
        mask.maxDepth = 1;
        return mask;
    }

}
