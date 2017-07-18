package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;

/**
 * @see com.PlanCategory.zebra.io.art.ArtifactCategory
 */
public class ArtifactTagMask
        extends CoNodeMask {

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

    public static ArtifactTagMask below(int maxDepth) {
        ArtifactTagMask mask = new ArtifactTagMask();
        mask.maxDepth = 1;
        return mask;
    }

}
