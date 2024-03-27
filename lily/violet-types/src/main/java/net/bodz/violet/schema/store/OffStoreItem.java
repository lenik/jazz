package net.bodz.violet.schema.store;

import javax.persistence.Table;

import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns("id")
@PrimaryKeyProperties("id")
@Table(schema = OffStoreItem.SCHEMA_NAME, name = OffStoreItem.TABLE_NAME)
public class OffStoreItem
        extends _OffStoreItem_stuff {

    private static final long serialVersionUID = 1L;

}
