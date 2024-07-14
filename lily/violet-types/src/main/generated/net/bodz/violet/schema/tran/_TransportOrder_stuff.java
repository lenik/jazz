package net.bodz.violet.schema.tran;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.violet.schema.shop.SalesOrder;
import net.bodz.violet.schema.store.StoreOrder;

@IdType(Long.class)
public abstract class _TransportOrder_stuff
        extends AbstractTransportOrder {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "tranodr";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PHASE_ID = "phase";
    public static final String FIELD_PREV_ID = "prev";
    public static final String FIELD_SALES_ORDER_ID = "saleodr";
    public static final String FIELD_STOREODR_ID = "storeodr";
    public static final String FIELD_SHIPPER_ID = "shipper";
    public static final String FIELD_SHIPCOST = "shipcost";
    public static final String FIELD_LENGTH = "length";
    public static final String FIELD_TOTAL_QUANTITY = "sum_qty";
    public static final String FIELD_TOTAL_AMOUNT = "sum_amount";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_PREV_ID = 19;
    public static final int N_SALES_ORDER_ID = 19;
    public static final int N_STOREODR_ID = 19;
    public static final int N_SHIPPER_ID = 10;
    public static final int N_SHIPCOST = 20;
    public static final int N_LENGTH = 10;
    public static final int N_TOTAL_QUANTITY = 20;
    public static final int N_TOTAL_AMOUNT = 20;

    private static final int _ord_FORM_ARGUMENTS = 19;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 2;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_PREV_ID = _ord_PHASE_ID + 1;
    private static final int _ord_SALES_ORDER_ID = _ord_PREV_ID + 1;
    private static final int _ord_STOREODR_ID = _ord_SALES_ORDER_ID + 1;
    private static final int _ord_SHIPPER_ID = _ord_STOREODR_ID + 1;
    private static final int _ord_SHIPCOST = 45;
    private static final int _ord_LENGTH = _ord_SHIPCOST + 1;
    private static final int _ord_TOTAL_QUANTITY = _ord_LENGTH + 1;
    private static final int _ord_TOTAL_AMOUNT = _ord_TOTAL_QUANTITY + 1;

    String formArguments;

    @NotNull
    BigDecimal shipcost;

    @NotNull
    int length;

    @NotNull
    BigDecimal totalQuantity;

    @NotNull
    BigDecimal totalAmount;

    /**  */
    TransportOrder prev;

    Long prevId;

    /**  */
    Organization shipper;

    Integer shipperId;

    /**  */
    @NotNull
    TransportCategory category;

    @NotNull
    int categoryId;

    /**  */
    SalesOrder salesOrder;

    Long salesOrderId;

    /**  */
    @NotNull
    TransportPhase phase;

    @NotNull
    int phaseId;

    /**  */
    StoreOrder storeodr;

    Long storeodrId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    @Ordinal(_ord_SHIPCOST)
    @NotNull
    @Precision(value = N_SHIPCOST, scale = 2)
    @Column(name = "shipcost", nullable = false, precision = 20, scale = 2)
    public BigDecimal getShipcost() {
        return shipcost;
    }

    public void setShipcost(@NotNull BigDecimal value) {
        this.shipcost = value;
    }

    @Ordinal(_ord_LENGTH)
    @Precision(value = 10)
    @Column(name = "length", nullable = false, precision = 10)
    public int getLength() {
        return length;
    }

    public void setLength(int value) {
        this.length = value;
    }

    @Ordinal(_ord_TOTAL_QUANTITY)
    @NotNull
    @Precision(value = N_TOTAL_QUANTITY, scale = 2)
    @Column(name = "sum_qty", nullable = false, precision = 20, scale = 2)
    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(@NotNull BigDecimal value) {
        this.totalQuantity = value;
    }

    @Ordinal(_ord_TOTAL_AMOUNT)
    @NotNull
    @Precision(value = N_TOTAL_AMOUNT, scale = 2)
    @Column(name = "sum_amount", nullable = false, precision = 20, scale = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NotNull BigDecimal value) {
        this.totalAmount = value;
    }

    /**
     *
     * @constraint foreign key (prev) references violet.tranodr (id)
     */
    @JoinColumn(name = "prev")
    @ManyToOne
    public TransportOrder getPrev() {
        return prev;
    }

    /**
     */
    public void setPrev(TransportOrder value) {
        this.prev = value;
    }

    @Ordinal(_ord_PREV_ID)
    @Precision(value = N_PREV_ID)
    @Column(name = "prev", precision = 19)
    public synchronized Long getPrevId() {
        if (prev != null) {
            return prev.getId();
        }
        return prevId;
    }

    public synchronized void setPrevId(Long value) {
        this.prevId = value;
    }

    /**
     *
     * @constraint foreign key (shipper) references lily.org (id)
     */
    @JoinColumn(name = "shipper")
    @ManyToOne
    public Organization getShipper() {
        return shipper;
    }

    /**
     */
    public void setShipper(Organization value) {
        this.shipper = value;
    }

    @Ordinal(_ord_SHIPPER_ID)
    @Precision(value = N_SHIPPER_ID)
    @Column(name = "shipper", precision = 10)
    public synchronized Integer getShipperId() {
        if (shipper != null) {
            return shipper.getId();
        }
        return shipperId;
    }

    public synchronized void setShipperId(Integer value) {
        this.shipperId = value;
    }

    /**
     *
     * @constraint foreign key (cat) references violet.trancat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    @NotNull
    public TransportCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull TransportCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            if (category.getId() == null)
                return 0;
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     *
     * @constraint foreign key (saleodr) references violet.saleodr (id)
     */
    @JoinColumn(name = "saleodr")
    @ManyToOne
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    /**
     */
    public void setSalesOrder(SalesOrder value) {
        this.salesOrder = value;
    }

    @Ordinal(_ord_SALES_ORDER_ID)
    @Precision(value = N_SALES_ORDER_ID)
    @Column(name = "saleodr", precision = 19)
    public synchronized Long getSalesOrderId() {
        if (salesOrder != null) {
            return salesOrder.getId();
        }
        return salesOrderId;
    }

    public synchronized void setSalesOrderId(Long value) {
        this.salesOrderId = value;
    }

    /**
     *
     * @constraint foreign key (phase) references violet.tranphase (id)
     */
    @JoinColumn(name = "phase")
    @ManyToOne
    @NotNull
    public TransportPhase getPhase() {
        return phase;
    }

    /**
     */
    public void setPhase(@NotNull TransportPhase value) {
        this.phase = value;
    }

    @Ordinal(_ord_PHASE_ID)
    @Precision(value = 10)
    @Column(name = "phase", nullable = false, precision = 10)
    public synchronized int getPhaseId() {
        if (phase != null) {
            if (phase.getId() == null)
                return 0;
            return phase.getId();
        }
        return phaseId;
    }

    public synchronized void setPhaseId(int value) {
        this.phaseId = value;
    }

    /**
     *
     * @constraint foreign key (storeodr) references violet.storeodr (id)
     */
    @JoinColumn(name = "storeodr")
    @ManyToOne
    public StoreOrder getStoreodr() {
        return storeodr;
    }

    /**
     */
    public void setStoreodr(StoreOrder value) {
        this.storeodr = value;
    }

    @Ordinal(_ord_STOREODR_ID)
    @Precision(value = N_STOREODR_ID)
    @Column(name = "storeodr", precision = 19)
    public synchronized Long getStoreodrId() {
        if (storeodr != null) {
            return storeodr.getId();
        }
        return storeodrId;
    }

    public synchronized void setStoreodrId(Long value) {
        this.storeodrId = value;
    }

    public void initNotNulls() {
        this.shipcost = BigDecimal.ZERO;
        this.totalQuantity = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

}
