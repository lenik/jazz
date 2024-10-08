package net.bodz.violet.schema.shop;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _ShopItemFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "shopitem_fav";

    public static final String FIELD_SHOP_ITEM_ID = "shopitem";

    public static final int N_SHOP_ITEM_ID = 19;

    private static final int _ord_SHOP_ITEM_ID = 2;

    /**  */
    @NotNull
    ShopItem shopItem;

    @NotNull
    long shopItemId;

    /**
     *
     * @constraint foreign key (shopitem) references violet.shopitem (id)
     */
    @JoinColumn(name = "shopitem")
    @ManyToOne
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
