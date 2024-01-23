package net.bodz.violet.shop;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Long.class)
public abstract class _ShopFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "shop_fav";

    public static final String FIELD_SHOP_ID = "shop";

    public static final int N_SHOP_ID = 10;

    private static final int _ord_SHOP_ID = 2;

    /**  */
    @NotNull
    Shop shop;

    @NotNull
    int shopId;

    /**
     *
     * @label shop
     * @constraint foreign key (shop) references violet.shop (id)
     */
    @NotNull
    public Shop getShop() {
        return shop;
    }

    /**
     */
    public void setShop(@NotNull Shop value) {
        this.shop = value;
    }

    @Ordinal(_ord_SHOP_ID)
    @Precision(value = 10)
    @Column(name = "shop", nullable = false, precision = 10)
    public synchronized int getShopId() {
        if (shop != null) {
            return shop.getId();
        }
        return shopId;
    }

    public synchronized void setShopId(int value) {
        this.shopId = value;
    }

    public void initNotNulls() {
    }

}
