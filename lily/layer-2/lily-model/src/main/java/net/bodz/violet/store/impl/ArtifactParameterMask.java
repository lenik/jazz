package net.bodz.violet.store.impl;

import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.store.ArtifactParameter
 */
public class ArtifactParameterMask
        extends CoNodeMask {

    public static ArtifactParameterMask below(int maxDepth) {
        ArtifactParameterMask mask = new ArtifactParameterMask();
        mask.maxDepth = 1;
        return mask;
    }

}
