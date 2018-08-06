package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

/**
 * 商店标签
 */
@Table(name = "shoptag")
public class ShopTag
        extends CoTag<ShopTag> {

    private static final long serialVersionUID = 1L;

    public ShopTag() {
    }

    public ShopTag(ShopTag parent) {
        super(parent);
    }

}
