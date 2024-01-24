package net.bodz.violet.schema.shop;

import javax.persistence.Table;


/**
 * 商店分类
 */
@Table(schema = "violet", name = "shopcat")
public class ShopCategory
        extends _ShopCategory_stuff<ShopCategory> {

    private static final long serialVersionUID = 1L;

}
