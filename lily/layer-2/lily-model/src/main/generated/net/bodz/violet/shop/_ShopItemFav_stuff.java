package net.bodz.violet.shop;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Long.class)
public abstract class _ShopItemFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    private static final int _ord_SHOP_ITEM_ID = 2;

    /**  */
    @NotNull
    ShopItem shopItem;

    @NotNull
    long shopItemId;

    /**
     *
     * @label shopitem
     * @constraint foreign key (shopitem) references violet.shopitem (id)
     */
    @NotNull
    public ShopItem getShopItem() {
        return shopItem;
    }

    /**
     */
    public void setShopItem(@NotNull ShopItem value) {
        this.shopItem = value;
    }

    @Ordinal(_ord_SHOP_ITEM_ID)
    @Precision(value = 19)
    @Column(name = "shopitem", nullable = false, precision = 19)
    public synchronized long getShopItemId() {
        if (shopItem != null) {
            if (shopItem.getId() == null)
                return 0L;
            return shopItem.getId();
        }
        return shopItemId;
    }

    public synchronized void setShopItemId(long value) {
        this.shopItemId = value;
    }

    public void initNotNulls() {
    }

}
