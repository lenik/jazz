package net.bodz.violet.tran;

import java.math.BigDecimal;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.geo.Zone;
import net.bodz.lily.t.base.CoMessage;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.store.StoreOrder;

@IdType(Long.class)
public abstract class _TransportOrder_stuff
        extends CoMessage<Long> {

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
    public static final String FIELD_S_ALIAS = "s_alias";
    public static final String FIELD_S_CTPROPS = "s_ctprops";
    public static final String FIELD_S_ADDRESS1 = "s_address1";
    public static final String FIELD_S_ADDRESS2 = "s_address2";
    public static final String FIELD_S_ZONE_ID = "s_zone";
    public static final String FIELD_S_TEL = "s_tel";
    public static final String FIELD_S_TELOK = "s_telok";
    public static final String FIELD_S_EMAIL = "s_email";
    public static final String FIELD_S_EMAILOK = "s_emailok";
    public static final String FIELD_D_ALIAS = "d_alias";
    public static final String FIELD_D_CTPROPS = "d_ctprops";
    public static final String FIELD_D_ADDRESS1 = "d_address1";
    public static final String FIELD_D_ADDRESS2 = "d_address2";
    public static final String FIELD_D_ZONE_ID = "d_zone";
    public static final String FIELD_D_TEL = "d_tel";
    public static final String FIELD_D_TELOK = "d_telok";
    public static final String FIELD_D_EMAIL = "d_email";
    public static final String FIELD_D_EMAILOK = "d_emailok";
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
    public static final int N_S_ALIAS = 32;
    public static final int N_S_CTPROPS = 2147483647;
    public static final int N_S_ADDRESS1 = 80;
    public static final int N_S_ADDRESS2 = 80;
    public static final int N_S_ZONE_ID = 10;
    public static final int N_S_TEL = 20;
    public static final int N_S_TELOK = 1;
    public static final int N_S_EMAIL = 30;
    public static final int N_S_EMAILOK = 1;
    public static final int N_D_ALIAS = 32;
    public static final int N_D_CTPROPS = 2147483647;
    public static final int N_D_ADDRESS1 = 80;
    public static final int N_D_ADDRESS2 = 80;
    public static final int N_D_ZONE_ID = 10;
    public static final int N_D_TEL = 20;
    public static final int N_D_TELOK = 1;
    public static final int N_D_EMAIL = 30;
    public static final int N_D_EMAILOK = 1;
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
    private static final int _ord_S_ALIAS = _ord_SHIPPER_ID + 1;
    private static final int _ord_S_CTPROPS = _ord_S_ALIAS + 1;
    private static final int _ord_S_ADDRESS1 = _ord_S_CTPROPS + 1;
    private static final int _ord_S_ADDRESS2 = _ord_S_ADDRESS1 + 1;
    private static final int _ord_S_ZONE_ID = _ord_S_ADDRESS2 + 1;
    private static final int _ord_S_TEL = _ord_S_ZONE_ID + 1;
    private static final int _ord_S_TELOK = _ord_S_TEL + 1;
    private static final int _ord_S_EMAIL = _ord_S_TELOK + 1;
    private static final int _ord_S_EMAILOK = _ord_S_EMAIL + 1;
    private static final int _ord_D_ALIAS = _ord_S_EMAILOK + 1;
    private static final int _ord_D_CTPROPS = _ord_D_ALIAS + 1;
    private static final int _ord_D_ADDRESS1 = _ord_D_CTPROPS + 1;
    private static final int _ord_D_ADDRESS2 = _ord_D_ADDRESS1 + 1;
    private static final int _ord_D_ZONE_ID = _ord_D_ADDRESS2 + 1;
    private static final int _ord_D_TEL = _ord_D_ZONE_ID + 1;
    private static final int _ord_D_TELOK = _ord_D_TEL + 1;
    private static final int _ord_D_EMAIL = _ord_D_TELOK + 1;
    private static final int _ord_D_EMAILOK = _ord_D_EMAIL + 1;
    private static final int _ord_SHIPCOST = _ord_D_EMAILOK + 1;
    private static final int _ord_LENGTH = _ord_SHIPCOST + 1;
    private static final int _ord_TOTAL_QUANTITY = _ord_LENGTH + 1;
    private static final int _ord_TOTAL_AMOUNT = _ord_TOTAL_QUANTITY + 1;

    String formArguments;

    String sAlias;

    Object sCtprops;

    @NotNull
    String sAddress1;

    @NotNull
    String sAddress2;

    String sTel;

    @NotNull
    boolean sTelok;

    String sEmail;

    @NotNull
    boolean sEmailok;

    String dAlias;

    Object dCtprops;

    @NotNull
    String dAddress1;

    @NotNull
    String dAddress2;

    String dTel;

    @NotNull
    boolean dTelok;

    String dEmail;

    @NotNull
    boolean dEmailok;

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
    Zone dZone;

    Integer dZoneId;

    /**  */
    Zone sZone;

    Integer sZoneId;

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

    @Ordinal(_ord_S_ALIAS)
    @Precision(value = N_S_ALIAS)
    @TextInput(maxLength = N_S_ALIAS)
    @Column(name = "s_alias", length = N_S_ALIAS)
    public String getSAlias() {
        return sAlias;
    }

    public void setSAlias(String value) {
        this.sAlias = value;
    }

    @Ordinal(_ord_S_CTPROPS)
    @Precision(value = 2147483647)
    @Column(name = "s_ctprops", precision = 2147483647)
    public Object getSCtprops() {
        return sCtprops;
    }

    public void setSCtprops(Object value) {
        this.sCtprops = value;
    }

    @Ordinal(_ord_S_ADDRESS1)
    @NotNull
    @Precision(value = N_S_ADDRESS1)
    @TextInput(maxLength = N_S_ADDRESS1)
    @Column(name = "s_address1", nullable = false, length = N_S_ADDRESS1)
    public String getSAddress1() {
        return sAddress1;
    }

    public void setSAddress1(@NotNull String value) {
        this.sAddress1 = value;
    }

    @Ordinal(_ord_S_ADDRESS2)
    @NotNull
    @Precision(value = N_S_ADDRESS2)
    @TextInput(maxLength = N_S_ADDRESS2)
    @Column(name = "s_address2", nullable = false, length = N_S_ADDRESS2)
    public String getSAddress2() {
        return sAddress2;
    }

    public void setSAddress2(@NotNull String value) {
        this.sAddress2 = value;
    }

    @Ordinal(_ord_S_TEL)
    @Precision(value = N_S_TEL)
    @TextInput(maxLength = N_S_TEL)
    @Column(name = "s_tel", length = N_S_TEL)
    public String getSTel() {
        return sTel;
    }

    public void setSTel(String value) {
        this.sTel = value;
    }

    @Ordinal(_ord_S_TELOK)
    @Precision(value = 1)
    @Column(name = "s_telok", nullable = false, precision = 1)
    public boolean isSTelok() {
        return sTelok;
    }

    public void setSTelok(boolean value) {
        this.sTelok = value;
    }

    @Ordinal(_ord_S_EMAIL)
    @Precision(value = N_S_EMAIL)
    @TextInput(maxLength = N_S_EMAIL)
    @Column(name = "s_email", length = N_S_EMAIL)
    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String value) {
        this.sEmail = value;
    }

    @Ordinal(_ord_S_EMAILOK)
    @Precision(value = 1)
    @Column(name = "s_emailok", nullable = false, precision = 1)
    public boolean isSEmailok() {
        return sEmailok;
    }

    public void setSEmailok(boolean value) {
        this.sEmailok = value;
    }

    @Ordinal(_ord_D_ALIAS)
    @Precision(value = N_D_ALIAS)
    @TextInput(maxLength = N_D_ALIAS)
    @Column(name = "d_alias", length = N_D_ALIAS)
    public String getDAlias() {
        return dAlias;
    }

    public void setDAlias(String value) {
        this.dAlias = value;
    }

    @Ordinal(_ord_D_CTPROPS)
    @Precision(value = 2147483647)
    @Column(name = "d_ctprops", precision = 2147483647)
    public Object getDCtprops() {
        return dCtprops;
    }

    public void setDCtprops(Object value) {
        this.dCtprops = value;
    }

    @Ordinal(_ord_D_ADDRESS1)
    @NotNull
    @Precision(value = N_D_ADDRESS1)
    @TextInput(maxLength = N_D_ADDRESS1)
    @Column(name = "d_address1", nullable = false, length = N_D_ADDRESS1)
    public String getDAddress1() {
        return dAddress1;
    }

    public void setDAddress1(@NotNull String value) {
        this.dAddress1 = value;
    }

    @Ordinal(_ord_D_ADDRESS2)
    @NotNull
    @Precision(value = N_D_ADDRESS2)
    @TextInput(maxLength = N_D_ADDRESS2)
    @Column(name = "d_address2", nullable = false, length = N_D_ADDRESS2)
    public String getDAddress2() {
        return dAddress2;
    }

    public void setDAddress2(@NotNull String value) {
        this.dAddress2 = value;
    }

    @Ordinal(_ord_D_TEL)
    @Precision(value = N_D_TEL)
    @TextInput(maxLength = N_D_TEL)
    @Column(name = "d_tel", length = N_D_TEL)
    public String getDTel() {
        return dTel;
    }

    public void setDTel(String value) {
        this.dTel = value;
    }

    @Ordinal(_ord_D_TELOK)
    @Precision(value = 1)
    @Column(name = "d_telok", nullable = false, precision = 1)
    public boolean isDTelok() {
        return dTelok;
    }

    public void setDTelok(boolean value) {
        this.dTelok = value;
    }

    @Ordinal(_ord_D_EMAIL)
    @Precision(value = N_D_EMAIL)
    @TextInput(maxLength = N_D_EMAIL)
    @Column(name = "d_email", length = N_D_EMAIL)
    public String getDEmail() {
        return dEmail;
    }

    public void setDEmail(String value) {
        this.dEmail = value;
    }

    @Ordinal(_ord_D_EMAILOK)
    @Precision(value = 1)
    @Column(name = "d_emailok", nullable = false, precision = 1)
    public boolean isDEmailok() {
        return dEmailok;
    }

    public void setDEmailok(boolean value) {
        this.dEmailok = value;
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
     * @label prev
     * @constraint foreign key (prev) references violet.tranodr (id)
     */
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
     * @label shipper
     * @constraint foreign key (shipper) references lily.org (id)
     */
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
     * @label cat
     * @constraint foreign key (cat) references violet.trancat (id)
     */
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
     * @label saleodr
     * @constraint foreign key (saleodr) references violet.saleodr (id)
     */
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
     * @label phase
     * @constraint foreign key (phase) references violet.tranphase (id)
     */
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
     * @label d_zone
     * @constraint foreign key (d_zone) references lily.zone (id)
     */
    public Zone getDZone() {
        return dZone;
    }

    /**
     */
    public void setDZone(Zone value) {
        this.dZone = value;
    }

    @Ordinal(_ord_D_ZONE_ID)
    @Precision(value = N_D_ZONE_ID)
    @Column(name = "d_zone", precision = 10)
    public synchronized Integer getDZoneId() {
        if (dZone != null) {
            return dZone.getId();
        }
        return dZoneId;
    }

    public synchronized void setDZoneId(Integer value) {
        this.dZoneId = value;
    }

    /**
     *
     * @label s_zone
     * @constraint foreign key (s_zone) references lily.zone (id)
     */
    public Zone getSZone() {
        return sZone;
    }

    /**
     */
    public void setSZone(Zone value) {
        this.sZone = value;
    }

    @Ordinal(_ord_S_ZONE_ID)
    @Precision(value = N_S_ZONE_ID)
    @Column(name = "s_zone", precision = 10)
    public synchronized Integer getSZoneId() {
        if (sZone != null) {
            return sZone.getId();
        }
        return sZoneId;
    }

    public synchronized void setSZoneId(Integer value) {
        this.sZoneId = value;
    }

    /**
     *
     * @label storeodr
     * @constraint foreign key (storeodr) references violet.storeodr (id)
     */
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
        this.sAddress1 = "";
        this.sAddress2 = "";
        this.dAddress1 = "";
        this.dAddress2 = "";
        this.shipcost = BigDecimal.ZERO;
        this.totalQuantity = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

}
