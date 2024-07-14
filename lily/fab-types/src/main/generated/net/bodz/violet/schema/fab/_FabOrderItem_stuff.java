package net.bodz.violet.schema.fab;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImagedEvent;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;

@IdType(Long.class)
public abstract class _FabOrderItem_stuff
        extends CoImagedEvent<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabodrl";

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

    private static final int _ord_ORDER_ID = 12;
    private static final int _ord_ARTIFACT_ID = _ord_ORDER_ID + 1;
    private static final int _ord_RESALE = _ord_ARTIFACT_ID + 1;
    private static final int _ord_ALT_LABEL = _ord_RESALE + 1;
    private static final int _ord_ALT_SPEC = _ord_ALT_LABEL + 1;
    private static final int _ord_ALT_UOM = _ord_ALT_SPEC + 1;
    private static final int _ord_QUANTITY = _ord_ALT_UOM + 1;
    private static final int _ord_PRICE = _ord_QUANTITY + 1;
    private static final int _ord_AMOUNT = _ord_PRICE + 1;
    private static final int _ord_NOTES = _ord_AMOUNT + 1;

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
     * @constraint foreign key (odr) references violet.fabodr (id)
     */
    @JoinColumn(name = "odr")
    @ManyToOne
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
