package net.bodz.violet.asset.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.asset.OrgUnitAsset;

/**
* @label OrgUnitAsset
*/
@ObjectType(OrgUnitAsset.class)
public class OrgUnitAssetIndex
        extends CoIndex<OrgUnitAsset, OrgUnitAssetCriteriaBuilder> {

    public OrgUnitAssetIndex() {
    }

}
