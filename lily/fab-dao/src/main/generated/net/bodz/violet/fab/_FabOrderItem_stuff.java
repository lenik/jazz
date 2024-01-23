package net.bodz.violet.fab;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

@IdType(Long.class)
public abstract class _FabOrderItem_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabodrl";

    public static final String FIELD_ID = "id";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_ORDER_ID = "odr";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_RESALE = "resale";
    public static final String FIELD_ALT_LABEL = "o_label";
    public static final String FIELD_ALT_SPEC = "o_spec";
    public static final String FIELD_ALT_UOM = "o_uom";
    public static final String FIELD_QUANTITY = "qty";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_NOTES = "notes";

    public static final int N_ID = 19;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_ORDER_ID = 19;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_RESALE = 1;
    public static final int N_ALT_LABEL = 30;
    public static final int N_ALT_SPEC = 80;
    public static final int N_ALT_UOM = 30;
    public static final int N_QUANTITY = 20;
    public static final int N_PRICE = 20;
    public static final int N_AMOUNT = 20;
    public static final int N_NOTES = 200;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = _ord_ID + 7;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_ORDER_ID = _ord_YEAR + 1;
    private static final int _ord_ARTIFACT_ID = _ord_ORDER_ID + 1;
    private static final int _ord_RESALE = _ord_ARTIFACT_ID + 1;
    private static final int _ord_ALT_LABEL = _ord_RESALE + 1;
    private static final int _ord_ALT_SPEC = _ord_ALT_LABEL + 1;
    private static final int _ord_ALT_UOM = _ord_ALT_SPEC + 1;
    private static final int _ord_QUANTITY = _ord_ALT_UOM + 1;
    private static final int _ord_PRICE = _ord_QUANTITY + 1;
    private static final int _ord_AMOUNT = _ord_PRICE + 1;
    private static final int _ord_NOTES = _ord_AMOUNT + 1;

    @Id
    @NotNull
    long id;

    ZonedDateTime beginTime;

    ZonedDateTime endTime;

    @NotNull
    int year;

    @NotNull
    boolean resale;

    String altLabel;

    String altSpec;

    String altUom;

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
    FabOrder order;

    @NotNull
    long orderId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public ZonedDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(ZonedDateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Ordinal(_ord_RESALE)
    @Precision(value = 1)
    @Column(name = "resale", nullable = false, precision = 1)
    public boolean isResale() {
        return resale;
    }

    public void setResale(boolean value) {
        this.resale = value;
    }

    @Ordinal(_ord_ALT_LABEL)
    @Precision(value = N_ALT_LABEL)
    @TextInput(maxLength = N_ALT_LABEL)
    @Column(name = "o_label", length = N_ALT_LABEL)
    public String getAltLabel() {
        return altLabel;
    }

    public void setAltLabel(String value) {
        this.altLabel = value;
    }

    @Ordinal(_ord_ALT_SPEC)
    @Precision(value = N_ALT_SPEC)
    @TextInput(maxLength = N_ALT_SPEC)
    @Column(name = "o_spec", length = N_ALT_SPEC)
    public String getAltSpec() {
        return altSpec;
    }

    public void setAltSpec(String value) {
        this.altSpec = value;
    }

    @Ordinal(_ord_ALT_UOM)
    @Precision(value = N_ALT_UOM)
    @TextInput(maxLength = N_ALT_UOM)
    @Column(name = "o_uom", length = N_ALT_UOM)
    public String getAltUom() {
        return altUom;
    }

    public void setAltUom(String value) {
        this.altUom = value;
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

    /**
     *
     * @label odr
     * @constraint foreign key (odr) references violet.fabodr (id)
     */
    @NotNull
    public FabOrder getOrder() {
        return order;
    }

    /**
     */
    public void setOrder(@NotNull FabOrder value) {
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

    public void initNotNulls() {
        this.quantity = BigDecimal.ZERO;
        this.price = BigDecimal.ZERO;
        this.amount = BigDecimal.ZERO;
    }

}
