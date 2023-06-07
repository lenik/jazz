package net.bodz.violet.shop;

import javax.persistence.Table;


/**
 * 陈列分类
 */
@Table(schema = "violet", name = "shopitemcat")
public class ShopItemCategory
        extends _ShopItemCategory_stuff<ShopItemCategory> {

    private static final long serialVersionUID = 1L;

}
