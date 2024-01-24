package net.bodz.violet.schema.art;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Artifact_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "art";

    public static final String FIELD_ID = "id";
    public static final String FIELD_SKU_CODE = "sku";
    public static final String FIELD_BAR_CODE = "barcode";
    public static final String FIELD_RFID_CODE = "rfid";
    public static final String FIELD_MODEL_NAME = "model";
    public static final String FIELD_PROTO_ID = "proto";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PHASE_ID = "phase";
    public static final String FIELD_UOM_ID = "uom";
    public static final String FIELD_FINISH = "finish";
    public static final String FIELD_PRICE = "price";

    public static final int N_ID = 10;
    public static final int N_SKU_CODE = 30;
    public static final int N_BAR_CODE = 30;
    public static final int N_RFID_CODE = 30;
    public static final int N_MODEL_NAME = 80;
    public static final int N_PROTO_ID = 10;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_UOM_ID = 10;
    public static final int N_FINISH = 5;
    public static final int N_PRICE = 12;

    private static final int _ord_ID = 1;
    private static final int _ord_SKU_CODE = _ord_ID + 1;
    private static final int _ord_BAR_CODE = _ord_SKU_CODE + 1;
    private static final int _ord_RFID_CODE = _ord_BAR_CODE + 1;
    private static final int _ord_MODEL_NAME = 17;
    private static final int _ord_PROTO_ID = _ord_MODEL_NAME + 1;
    private static final int _ord_CATEGORY_ID = _ord_PROTO_ID + 1;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_UOM_ID = _ord_PHASE_ID + 1;
    private static final int _ord_FINISH = _ord_UOM_ID + 2;
    private static final int _ord_PRICE = _ord_FINISH + 1;

    @Id
    @NotNull
    int id;

    String skuCode;

    String barCode;

    String rfidCode;

    String modelName;

    @NotNull
    short finish;

    BigDecimal price;

    /**  */
    Artifact proto;

    Integer protoId;

    /**  */
    ArtifactPhase phase;

    Integer phaseId;

    /**  */
    UOM uom;

    Integer uomId;

    /**  */
    ArtifactCategory category;

    Integer categoryId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_SKU_CODE)
    @Precision(value = N_SKU_CODE)
    @TextInput(maxLength = N_SKU_CODE)
    @Column(name = "sku", length = N_SKU_CODE)
    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String value) {
        this.skuCode = value;
    }

    @Ordinal(_ord_BAR_CODE)
    @Precision(value = N_BAR_CODE)
    @TextInput(maxLength = N_BAR_CODE)
    @Column(name = "barcode", length = N_BAR_CODE)
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String value) {
        this.barCode = value;
    }

    @Ordinal(_ord_RFID_CODE)
    @Precision(value = N_RFID_CODE)
    @TextInput(maxLength = N_RFID_CODE)
    @Column(name = "rfid", length = N_RFID_CODE)
    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String value) {
        this.rfidCode = value;
    }

    @Ordinal(_ord_MODEL_NAME)
    @Precision(value = N_MODEL_NAME)
    @TextInput(maxLength = N_MODEL_NAME)
    @Column(name = "model", length = N_MODEL_NAME)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String value) {
        this.modelName = value;
    }

    @Ordinal(_ord_FINISH)
    @Precision(value = 5)
    @Column(name = "finish", nullable = false, precision = 5)
    public short getFinish() {
        return finish;
    }

    public void setFinish(short value) {
        this.finish = value;
    }

    @Ordinal(_ord_PRICE)
    @Precision(value = N_PRICE, scale = 2)
    @Column(name = "price", precision = 12, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     *
     * @label proto
     * @constraint foreign key (proto) references violet.art (id)
     */
    public Artifact getProto() {
        return proto;
    }

    /**
     */
    public void setProto(Artifact value) {
        this.proto = value;
    }

    @Ordinal(_ord_PROTO_ID)
    @Precision(value = N_PROTO_ID)
    @Column(name = "proto", precision = 10)
    public synchronized Integer getProtoId() {
        if (proto != null) {
            return proto.getId();
        }
        return protoId;
    }

    public synchronized void setProtoId(Integer value) {
        this.protoId = value;
    }

    /**
     *
     * @label phase
     * @constraint foreign key (phase) references violet.artphase (id)
     */
    public ArtifactPhase getPhase() {
        return phase;
    }

    /**
     */
    public void setPhase(ArtifactPhase value) {
        this.phase = value;
    }

    @Ordinal(_ord_PHASE_ID)
    @Precision(value = N_PHASE_ID)
    @Column(name = "phase", precision = 10)
    public synchronized Integer getPhaseId() {
        if (phase != null) {
            return phase.getId();
        }
        return phaseId;
    }

    public synchronized void setPhaseId(Integer value) {
        this.phaseId = value;
    }

    /**
     *
     * @label uom
     * @constraint foreign key (uom) references lily.uom (id)
     */
    public UOM getUom() {
        return uom;
    }

    /**
     */
    public void setUom(UOM value) {
        this.uom = value;
    }

    @Ordinal(_ord_UOM_ID)
    @Precision(value = N_UOM_ID)
    @Column(name = "uom", precision = 10)
    public synchronized Integer getUomId() {
        if (uom != null) {
            return uom.getId();
        }
        return uomId;
    }

    public synchronized void setUomId(Integer value) {
        this.uomId = value;
    }

    /**
     *
     * @label cat
     * @constraint foreign key (cat) references violet.artcat (id)
     */
    public ArtifactCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(ArtifactCategory value) {
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

    public void initNotNulls() {
    }

}
