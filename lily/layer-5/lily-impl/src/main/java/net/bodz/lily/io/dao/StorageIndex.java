package net.bodz.lily.io.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.io.Storage;
import net.bodz.lily.model.base.CoIndex;

/**
* @label Storage
*/
@ObjectType(Storage.class)
public class StorageIndex
        extends CoIndex<Storage, StorageMask> {

    public StorageIndex() {
    }

}
