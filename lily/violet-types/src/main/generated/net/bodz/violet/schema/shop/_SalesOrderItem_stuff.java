package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImagedEvent;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;

@IdType(Long.class)
public abstract class _SalesOrderItem_stuff
        extends CoImagedEvent<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "saleodrl";

    public static final String FIELD_ORDER_ID = "odr";
    public static final String FIELD_SHOP_ITEM_ID = "shopitem";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_QUANTITY = "qty";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_N1 = "n1";

    public static final int N_ORDER_ID = 19;
    public static final int N_SHOP_ITEM_ID = 19;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_BATCH = 2147483647;
    public static final int N_QUANTITY = 20;
    public static final int N_PRICE = 20;
    public static final int N_AMOUNT = 20;
    public static final int N_N1 = 20;

    private static final int _ord_ORDER_ID = 10;
    private static final int _ord_SHOP_ITEM_ID = _ord_ORDER_ID + 1;
    private static final int _ord_ARTIFACT_ID = _ord_SHOP_ITEM_ID + 1;
    private static final int _ord_BATCH = _ord_ARTIFACT_ID + 1;
    private static final int _ord_QUANTITY = _ord_BATCH + 1;
    private static final int _ord_PRICE = _ord_QUANTITY + 1;
    private static final int _ord_AMOUNT = _ord_PRICE + 1;
    private static final int _ord_N1 = _ord_AMOUNT + 1;

    JsonVariant batch;

    @NotNull
    BigDecimal quantity;

    @NotNull
    BigDecimal price;

    @NotNull
    BigDecimal amount;

    @NotNull
    BigDecimal n1;

    /**  */
    @NotNull
    SalesOrder order;

    @NotNull
    long orderId;

    /**  */
    ShopItem shopItem;

    Long shopItemId;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public JsonVariant getBatch() {
        return batch;
    }

    public void setBatch(JsonVariant value) {
        this.batch = value;
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

    @Ordinal(_ord_AMOUNT)
    @NotNull
    @Precision(value = N_AMOUNT, scale = 2)
    @Column(name = "amount", nullable = false, precision = 20, scale = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull BigDecimal value) {
        this.amount = value;
    }

    @Ordinal(_ord_N1)
    @NotNull
    @Precision(value = N_N1, scale = 2)
    @Column(name = "n1", nullable = false, precision = 20, scale = 2)
    public BigDecimal getN1() {
        return n1;
    }

    public void setN1(@NotNull BigDecimal value) {
        this.n1 = value;
    }

    /**
     *
     * @constraint foreign key (odr) references violet.saleodr (id)
     */
    @JoinColumn(name = "odr")
    @ManyToOne
    @NotNull
    public SalesOrder getOrder() {
        return order;
    }

    /**
     */
    public void setOrder(@NotNull SalesOrder value) {
        this.order = value;
    }

    @Ordinal(_ord_ORDER_ID)
    @Precision(value = 19)
    @Column(name = "odr", nullable = false, precision = 19)
    public synchronized long getOrderId() {
        if (order != null) {
            if (order.getId() == null)
                return 0L;
            return order.getId();
        }
        return orderId;
    }

    public synchronized void setOrderId(long value) {
        this.orderId = value;
    }

    /**
     *
     * @constraint foreign key (shopitem) references violet.shopitem (id)
     */
    @JoinColumn(name = "shopitem")
    @ManyToOne
    public ShopItem getShopItem() {
        return shopItem;
    }

    /**
     */
    public void setShopItem(ShopItem value) {
        this.shopItem = value;
    }

    @Ordinal(_ord_SHOP_ITEM_ID)
    @Precision(value = N_SHOP_ITEM_ID)
    @Column(name = "shopitem", precision = 19)
    public synchronized Long getShopItemId() {
        if (shopItem != null) {
            return shopItem.getId();
        }
        return shopItemId;
    }

    public synchronized void setShopItemId(Long value) {
        this.shopItemId = value;
    }

    /**
     *
     * @constraint foreign key (art) references violet.art (id)
     */
    @JoinColumn(name = "art")
    @ManyToOne
    @NotNull
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     */
    public void setArtifact(@NotNull Artifact value) {
        this.artifact = value;
    }

    @Ordinal(_ord_ARTIFACT_ID)
    @Precision(value = 10)
    @Column(name = "art", nullable = false, precision = 10)
    public synchronized int getArtifactId() {
        if (artifact != null) {
            if (artifact.getId() == null)
                return 0;
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(int value) {
        this.artifactId = value;
    }

    public void initNotNulls() {
        this.quantity = BigDecimal.ZERO;
        this.price = BigDecimal.ZERO;
        this.amount = BigDecimal.ZERO;
        this.n1 = BigDecimal.ZERO;
    }

}
