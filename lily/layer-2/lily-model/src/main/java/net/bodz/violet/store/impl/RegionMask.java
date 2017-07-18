package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoNodeMask;

public class RegionMask
        extends CoNodeMask {

    Integer asArtifactId;
    Integer forArtifactCategoryId;
    Integer forArtifactId;
    Integer categoryId;
    Integer tagId;

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);

        // QVariantMap<String> map = QVariantMap.from(_map);
    }

}
