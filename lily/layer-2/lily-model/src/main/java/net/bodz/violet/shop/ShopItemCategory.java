package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCategory;

/**
 * 陈列分类
 */
@IdType(Integer.class)
@Table(name = "shopitemcat")
public class ShopItemCategory
        extends CoCategory<ShopItemCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public ShopItemCategory() {
        super();
    }

    public ShopItemCategory(ShopItemCategory parent) {
        super(parent);
    }

}
