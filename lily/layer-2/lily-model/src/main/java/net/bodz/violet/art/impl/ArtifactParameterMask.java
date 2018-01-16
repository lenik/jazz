package net.bodz.violet.art.impl;

import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.art.ArtifactParameter
 */
public class ArtifactParameterMask
        extends CoNodeMask {

    public static ArtifactParameterMask below(int maxDepth) {
        ArtifactParameterMask mask = new ArtifactParameterMask();
        mask.maxDepth = 1;
        return mask;
    }

}
