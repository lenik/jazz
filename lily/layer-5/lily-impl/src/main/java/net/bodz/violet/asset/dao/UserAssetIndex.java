package net.bodz.violet.asset.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.asset.UserAsset;

/**
* @label UserAsset
*/
@ObjectType(UserAsset.class)
public class UserAssetIndex
        extends CoIndex<UserAsset, UserAssetCriteriaBuilder> {

    public UserAssetIndex() {
    }

}
