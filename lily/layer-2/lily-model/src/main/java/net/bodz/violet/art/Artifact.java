package net.bodz.violet.art;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Table;

import org.json.JSONObject;

import net.bodz.bas.c.org.json.IJsonForm;
import net.bodz.bas.c.org.json.JsonObj;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.typer.std.IAttributes;
import net.bodz.bas.typer.std.ITyperFamily;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.repr.EntGroup;

/**
 * 物品
 */
@IdType(Integer.class)
// @SchemaPref(Schemas.ARTIFACT)
@Table(name = "art")
public class Artifact
        extends CoEntity<Integer>
        implements IAttributes {

    private static final long serialVersionUID = 1L;

    public static final int N_SKU_CODE = 30;
    public static final int N_BAR_CODE = 30;
    public static final int N_UOM_PROPERTY = 20;
    public static final int N_MODEL_NAME = 100;
    public static final int N_COLOR = 20;
    public static final int N_CAUTION = 100;

    static final String A_COLOR = "color";
    static final String A_CAUTION = "caution";
    static final String A_SUPPLY_DELAY = "supply.delay";

    private ArtifactCategory category;
    private ArtifactPhase phase;
    private Set<ArtifactTag> tags = new HashSet<>();

    private String skuCode;
    private String barCode;
    private String modelName;
    private int finish;

    private UOM uom = new UOM(UOMs.PIECE);
    private String uomProperty = "数量";
    private Map<UOM, Double> convMap = new HashMap<UOM, Double>();
    private int decimalDigits = 2;

    private Map<String, Object> attributes = new TreeMap<>();

    private BigDecimal price = BigDecimal.ZERO;

    /**
     * 分类
     */
    @OfGroup(StdGroup.Classification.class)
    public ArtifactCategory getCategory() {
        return category;
    }

    public void setCategory(ArtifactCategory category) {
        this.category = category;
    }

    /**
     * 成熟度
     */
    public ArtifactPhase getPhase() {
        return phase;
    }

    public void setPhase(ArtifactPhase phase) {
        this.phase = phase;
    }

    /**
     * 标签集
     */
    public Set<ArtifactTag> getTags() {
        return tags;
    }

    public void setTags(Set<ArtifactTag> tags) {
        if (tags == null)
            throw new NullPointerException("tags");
        this.tags = tags;
    }

    /**
     * 存货识别码。
     *
     * @label SKU
     * @placeholder 输入存货识别码…
     */
    @OfGroup(StdGroup.Identity.class)
    @TextInput(maxLength = N_SKU_CODE)
    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * 条形码
     *
     * @placeholder 输入条形码
     */
    @OfGroup({ StdGroup.Identity.class, EntGroup.Packaging.class })
    @TextInput(maxLength = N_BAR_CODE)
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    /**
     * 完成形态度量
     *
     * @see ArtifactPhase
     */
    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    /**
     * 单位
     */
    @OfGroup(EntGroup.Packaging.class)
    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }

    /**
     * 衡量单位的用途属性，如"长度"、"重量"等。
     *
     * @label 度量属性
     * @placeholder 输入衡量单位的用途属性，如"长度"、"重量"
     */
    @OfGroup(EntGroup.Packaging.class)
    @TextInput(maxLength = N_UOM_PROPERTY)
    public String getUomProperty() {
        return uomProperty;
    }

    public void setUomProperty(String uomProperty) {
        this.uomProperty = uomProperty;
    }

    /**
     * 单位转换表
     */
    @OfGroup(EntGroup.Packaging.class)
    public Map<UOM, Double> getConvMap() {
        return convMap;
    }

    public void setConvMap(Map<UOM, Double> convMap) {
        if (convMap == null)
            throw new NullPointerException("convMap");
        this.convMap = convMap;
    }

    /**
     * 小数位数
     *
     * 描述该种物料时使用的数量精度。
     */
    @OfGroup(StdGroup.Settings.class)
    @FormInput(textWidth = 3)
    @NumericInput(min = 0, max = 10)
    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * 规格型号
     *
     * @placeholder 输入规格/型号…
     */
    @OfGroup(StdGroup.Identity.class)
    @TextInput(maxLength = N_MODEL_NAME)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * 定价
     */
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /** ⇱ Implementation Of {@link IAttributes}. */
    /* _____________________________ */static section.iface __ATTRS__;

    @Override
    public Collection<String> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        T val = (T) attributes.get(name);
        if (val == null)
            val = defaultValue;
        return val;
    }

    @Override
    public ITyperFamily<?> getAttributeTypers(String attributeName) {
        return null;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public IJsonForm getAttributesJson() {
        JSONObject obj = new JSONObject(attributes);
        return new JsonObj(obj);
    }

    public void setAttributesJson(IJsonForm form) {
        String json = form.readInStr();
        attributes = JsonFn.toMap(json);
    }

    /**
     * 供应延时 (天)
     *
     * 指开始订购到收到货品实物的最大时间间隔，用于指导采购、生产计划。
     */
    @OfGroup(StdGroup.Schedule.class)
    public int getSupplyDelay() {
        return getAttribute(A_SUPPLY_DELAY, 0);
    }

    public void setSupplyDelay(int supplyDelay) {
        attributes.put(A_SUPPLY_DELAY, supplyDelay);
    }

    /**
     * 颜色
     *
     * 描述物料的外观颜色。
     *
     * 对于系列商品不同的颜色型号，应该分别建立不同的物料。
     *
     * @placeholder 输入颜色名称…
     */
    @OfGroup(EntGroup.ColorManagement.class)
    @TextInput(maxLength = N_COLOR)
    public String getColor() {
        return (String) attributes.get(A_COLOR);
    }

    public void setColor(String color) {
        attributes.put(A_COLOR, color);
    }

    /**
     * 警告提示
     *
     * 如危险品、易碎、易爆炸等。
     *
     * @placeholder 输入警告信息…
     */
    @Priority(100)
    @TextInput(maxLength = N_CAUTION)
    public String getCaution() {
        return (String) attributes.get(A_CAUTION);
    }

    public void setCaution(String caution) {
        attributes.put(A_CAUTION, caution);
    }

}
