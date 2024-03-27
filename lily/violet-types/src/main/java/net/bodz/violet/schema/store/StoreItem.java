package net.bodz.violet.schema.store;

import javax.persistence.Table;

import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns("id")
@PrimaryKeyProperties("id")
@Table(schema = StoreItem.SCHEMA_NAME, name = StoreItem.TABLE_NAME)
public class StoreItem
        extends _StoreItem_stuff {

    private static final long serialVersionUID = 1L;

}
