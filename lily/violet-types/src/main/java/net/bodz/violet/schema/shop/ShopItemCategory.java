package net.bodz.violet.schema.shop;

import javax.persistence.Table;

@Table(schema = ShopItemCategory.SCHEMA_NAME, name = ShopItemCategory.TABLE_NAME)
public class ShopItemCategory
        extends _ShopItemCategory_stuff<ShopItemCategory> {

    private static final long serialVersionUID = 1L;

}
