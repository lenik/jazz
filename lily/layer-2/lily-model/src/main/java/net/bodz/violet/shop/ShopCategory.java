package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCategory;

/**
 * 商店分类
 */
@IdType(Integer.class)
@Table(name = "shopcat")
public class ShopCategory
        extends CoCategory<ShopCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public ShopCategory() {
        super();
    }

    public ShopCategory(ShopCategory parent) {
        super(parent);
    }

}
