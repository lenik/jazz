package net.bodz.violet.schema.shop;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

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
     * @constraint foreign key (shop) references violet.shop (id)
     */
    @JoinColumn(name = "shop")
    @ManyToOne
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
            if (shop.getId() == null)
                return 0;
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
