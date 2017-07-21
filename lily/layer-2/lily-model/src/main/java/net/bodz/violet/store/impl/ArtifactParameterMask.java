package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see net.bodz.violet.store.ArtifactParameter
 */
public class ArtifactParameterMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

    public static ArtifactParameterMask below(int maxDepth) {
        ArtifactParameterMask mask = new ArtifactParameterMask();
        mask.maxDepth = 1;
        return mask;
    }

}
