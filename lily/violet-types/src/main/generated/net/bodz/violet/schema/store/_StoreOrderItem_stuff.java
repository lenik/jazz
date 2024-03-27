package net.bodz.violet.schema.store;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImagedEvent;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;

@IdType(Long.class)
public abstract class _StoreOrderItem_stuff
        extends CoImagedEvent<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "storeodrl";

    public static final String FIELD_ORDER_ID = "odr";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_REGION_ID = "region";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_SERIAL = "serial";
    public static final String FIELD_EXPIRE = "expire";
    public static final String FIELD_QUANTITY = "qty";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_NOTES = "notes";

    public static final int N_ORDER_ID = 19;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_REGION_ID = 10;
    public static final int N_BATCH = 2147483647;
    public static final int N_SERIAL = 19;
    public static final int N_EXPIRE = 35;
    public static final int N_QUANTITY = 20;
    public static final int N_PRICE = 20;
    public static final int N_AMOUNT = 20;
    public static final int N_NOTES = 200;

    private static final int _ord_ORDER_ID = 8;
    private static final int _ord_ARTIFACT_ID = _ord_ORDER_ID + 1;
    private static final int _ord_REGION_ID = _ord_ARTIFACT_ID + 1;
    private static final int _ord_BATCH = _ord_REGION_ID + 1;
    private static final int _ord_SERIAL = _ord_BATCH + 1;
    private static final int _ord_EXPIRE = _ord_SERIAL + 1;
    private static final int _ord_QUANTITY = _ord_EXPIRE + 1;
    private static final int _ord_PRICE = _ord_QUANTITY + 1;
    private static final int _ord_AMOUNT = _ord_PRICE + 1;
    private static final int _ord_NOTES = _ord_AMOUNT + 1;

    JsonVariant batch;

    Long serial;

    OffsetDateTime expire;

    @NotNull
    BigDecimal quantity;

    @NotNull
    BigDecimal price;

    @NotNull
    BigDecimal amount;

    String notes;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    /**  */
    @NotNull
    StoreOrder order;

    @NotNull
    long orderId;

    /**  */
    @NotNull
    Region region;

    @NotNull
    int regionId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public JsonVariant getBatch() {
        return batch;
    }

    public void setBatch(JsonVariant value) {
        this.batch = value;
    }

    @Ordinal(_ord_SERIAL)
    @Precision(value = N_SERIAL)
    @Column(name = "serial", precision = 19)
    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long value) {
        this.serial = value;
    }

    @Ordinal(_ord_EXPIRE)
    @Precision(value = 35, scale = 6)
    @Column(name = "expire", precision = 35, scale = 6)
    public OffsetDateTime getExpire() {
        return expire;
    }

    public void setExpire(OffsetDateTime value) {
        this.expire = value;
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

    @Ordinal(_ord_NOTES)
    @Precision(value = N_NOTES)
    @TextInput(maxLength = N_NOTES)
    @Column(name = "notes", length = N_NOTES)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String value) {
        this.notes = value;
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

    /**
     *
     * @constraint foreign key (odr) references violet.storeodr (id)
     */
    @JoinColumn(name = "odr")
    @ManyToOne
    @NotNull
    public StoreOrder getOrder() {
        return order;
    }

    /**
     */
    public void setOrder(@NotNull StoreOrder value) {
        this.order = value;
    }

    @Ordinal(_ord_ORDER_ID)
    @Precision(value = 19)
    @Column(name = "odr", nullable = false, precision = 19)
    public synchronized long getOrderId() {
        if (order != null) {
            return order.getId();
        }
        return orderId;
    }

    public synchronized void setOrderId(long value) {
        this.orderId = value;
    }

    /**
     *
     * @constraint foreign key (region) references violet.region (id)
     */
    @JoinColumn(name = "region")
    @ManyToOne
    @NotNull
    public Region getRegion() {
        return region;
    }

    /**
     */
    public void setRegion(@NotNull Region value) {
        this.region = value;
    }

    @Ordinal(_ord_REGION_ID)
    @Precision(value = 10)
    @Column(name = "region", nullable = false, precision = 10)
    public synchronized int getRegionId() {
        if (region != null) {
            if (region.getId() == null)
                return 0;
            return region.getId();
        }
        return regionId;
    }

    public synchronized void setRegionId(int value) {
        this.regionId = value;
    }

    public void initNotNulls() {
        this.quantity = BigDecimal.ZERO;
        this.price = BigDecimal.ZERO;
        this.amount = BigDecimal.ZERO;
    }

}
