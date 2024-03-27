package net.bodz.violet.schema.shop;

import javax.persistence.Table;

@Table(schema = ShopCategory.SCHEMA_NAME, name = ShopCategory.TABLE_NAME)
public class ShopCategory
        extends _ShopCategory_stuff<ShopCategory> {

    private static final long serialVersionUID = 1L;

}
