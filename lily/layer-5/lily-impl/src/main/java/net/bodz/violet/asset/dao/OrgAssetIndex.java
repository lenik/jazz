package net.bodz.violet.asset.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.asset.OrgAsset;

/**
* @label OrgAsset
*/
@ObjectType(OrgAsset.class)
public class OrgAssetIndex
        extends CoIndex<OrgAsset, OrgAssetMask> {

    public OrgAssetIndex() {
    }

}
