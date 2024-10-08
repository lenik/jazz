package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _CartItem_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "cartitem";

    public static final String FIELD_SHOP_ITEM_ID = "shopitem";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_QUANTITY = "qty";

    public static final int N_SHOP_ITEM_ID = 19;
    public static final int N_PRICE = 20;
    public static final int N_QUANTITY = 20;

    private static final int _ord_SHOP_ITEM_ID = 15;
    private static final int _ord_PRICE = _ord_SHOP_ITEM_ID + 1;
    private static final int _ord_QUANTITY = _ord_PRICE + 1;

    @NotNull
    BigDecimal price;

    @NotNull
    BigDecimal quantity;

    /**  */
    @NotNull
    ShopItem shopItem;

    @NotNull
    long shopItemId;

    @Ordinal(_ord_PRICE)
    @NotNull
    @Precision(value = N_PRICE, scale = 2)
    @Column(name = "price", nullable = false, precision = 20, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull BigDecimal value) {
        this.price = value;
    }

    @Ordinal(_ord_QUANTITY)
    @NotNull
    @Precision(value = N_QUANTITY, scale = 2)
    @Column(name = "qty", nullable = false, precision = 20, scale = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull BigDecimal value) {
        this.quantity = value;
    }

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
        this.price = BigDecimal.ZERO;
        this.quantity = BigDecimal.ZERO;
    }

}
