package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 商店标签
 */
@IdType(Integer.class)
@Table(name = "shoptag")
public class ShopTag
        extends CoNode<ShopTag, Integer> {

    private static final long serialVersionUID = 1L;

    public ShopTag() {
    }

    public ShopTag(ShopTag parent) {
        super(parent);
    }

}
