package net.bodz.violet.shop;

import javax.persistence.Table;


/**
 * 商店标签
 */
@Table(schema = "violet", name = "shoptag")
public class ShopTag
        extends _ShopTag_stuff<ShopTag> {

    private static final long serialVersionUID = 1L;

}
