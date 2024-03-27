package net.bodz.violet.schema.store;

import javax.persistence.Table;

@Table(schema = StoreCategory.SCHEMA_NAME, name = StoreCategory.TABLE_NAME)
public class StoreCategory
        extends _StoreCategory_stuff<StoreCategory> {

    private static final long serialVersionUID = 1L;

}
