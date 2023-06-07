package net.bodz.violet.asset.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.asset.Asset;

/**
* @label Asset
*/
@ObjectType(Asset.class)
public class AssetIndex
        extends CoIndex<Asset, AssetMask> {

    public AssetIndex() {
    }

}
