package net.bodz.violet.asset.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.asset.PersonAsset;

/**
* @label PersonAsset
*/
@ObjectType(PersonAsset.class)
public class PersonAssetIndex
        extends CoIndex<PersonAsset, PersonAssetMask> {

    public PersonAssetIndex() {
    }

}