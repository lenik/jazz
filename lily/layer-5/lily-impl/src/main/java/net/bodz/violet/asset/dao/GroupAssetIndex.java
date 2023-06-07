package net.bodz.violet.asset.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.asset.GroupAsset;

/**
* @label GroupAsset
*/
@ObjectType(GroupAsset.class)
public class GroupAssetIndex
        extends CoIndex<GroupAsset, GroupAssetMask> {

    public GroupAssetIndex() {
    }

}
