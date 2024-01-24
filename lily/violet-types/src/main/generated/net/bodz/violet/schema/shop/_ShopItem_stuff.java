package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMomentInterval;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;

@IdType(Long.class)
public abstract class _ShopItem_stuff
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "shopitem";

    public static final String FIELD_SHOP_ID = "shop";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_QUANTITY = "qty";

    public static final int N_SHOP_ID = 10;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_BATCH = 2147483647;
    public static final int N_PRICE = 20;
    public static final int N_QUANTITY = 20;

    private static final int _ord_SHOP_ID = 14;
    private static final int _ord_CATEGORY_ID = _ord_SHOP_ID + 1;
    private static final int _ord_ARTIFACT_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_BATCH = _ord_ARTIFACT_ID + 1;
    private static final int _ord_PRICE = _ord_BATCH + 1;
    private static final int _ord_QUANTITY = _ord_PRICE + 1;

    Object batch;

    @NotNull
    BigDecimal price;

    @NotNull
    BigDecimal quantity;

    /**  */
    ShopItemCategory category;

    Integer categoryId;

    /**  */
    Shop shop;

    Integer shopId;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
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
     * @label cat
     * @constraint foreign key (cat) references violet.shopitemcat (id)
     */
    public ShopItemCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(ShopItemCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = N_CATEGORY_ID)
    @Column(name = "cat", precision = 10)
    public synchronized Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    /**
     *
     * @label shop
     * @constraint foreign key (shop) references violet.shop (id)
     */
    public Shop getShop() {
        return shop;
    }

    /**
     */
    public void setShop(Shop value) {
        this.shop = value;
    }

    @Ordinal(_ord_SHOP_ID)
    @Precision(value = N_SHOP_ID)
    @Column(name = "shop", precision = 10)
    public synchronized Integer getShopId() {
        if (shop != null) {
            return shop.getId();
        }
        return shopId;
    }

    public synchronized void setShopId(Integer value) {
        this.shopId = value;
    }

    /**
     *
     * @label art
     * @constraint foreign key (art) references violet.art (id)
     */
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
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(int value) {
        this.artifactId = value;
    }

    public void initNotNulls() {
        this.price = BigDecimal.ZERO;
        this.quantity = BigDecimal.ZERO;
    }

}
