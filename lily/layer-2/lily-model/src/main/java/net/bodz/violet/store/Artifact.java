package net.bodz.violet.store;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.t.variant.IVariantMap;
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
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_SKU_CODE = 30;
    public static final int N_BAR_CODE = 30;
    public static final int N_UOM_PROPERTY = 20;
    public static final int N_MODEL_NAME = 100;
    public static final int N_COLOR = 20;
    public static final int N_CAUTION = 100;

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

    private String color;
    private String caution;

    private Dim3d bbox = new Dim3d();
    private double quantity = 1.0;
    private double weight;
    private double netWeight;

    private int supplyDelay; // in days

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
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    /**
     * 装箱尺寸 (mm)
     * 
     * 是指将商品装入一个长方体的箱子，长方体的形状用<code>长 x 宽 x 高</code>来描述。
     * <p>
     * 尺寸的单位采用国际通行的毫米(mm)制。
     * <p>
     * 如果有多种装箱规格（如小包装、大包装），这里仅可以描述一种。应该选择合适的、常用的装箱规格来描述。
     */
    @OfGroup(EntGroup.Packaging.class)
    @Priority(100)
    public Dim3d getBbox() {
        return bbox;
    }

    public void setBbox(Dim3d bbox) {
        this.bbox = bbox;
    }

    /**
     * 装箱数量
     * 
     * 如果有多种装箱规格（如小包装、大包装），这里仅可以描述一种。应该选择合适的、常用的装箱规格来描述。
     */
    @OfGroup(EntGroup.Packaging.class)
    @Priority(101)
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * 毛重 (g)
     * 
     * 含包装的重量。
     * 
     * 单位采用国际通行的克(g)。
     */
    @OfGroup(EntGroup.Packaging.class)
    @Priority(200)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 净重 (g)
     * 
     * 不含包装的重量。
     * 
     * 单位采用国际通行的克(g)。
     */
    @OfGroup(EntGroup.Packaging.class)
    @Priority(201)
    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * 供应延时 (天)
     * 
     * 指开始订购到收到货品实物的最大时间间隔，用于指导采购、生产计划。
     */
    @OfGroup(StdGroup.Schedule.class)
    public int getSupplyDelay() {
        return supplyDelay;
    }

    public void setSupplyDelay(int supplyDelay) {
        this.supplyDelay = supplyDelay;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        skuCode = map.getString("skuCode", skuCode);
        barCode = map.getString("skuCode", barCode);
        modelName = map.getString("modelName", modelName);
        finish = map.getInt("finish", finish);

        // uom
        uomProperty = map.getString("uomProperty", uomProperty);
        // convMap
        decimalDigits = map.getInt("decimalDigits", decimalDigits);

        color = map.getString("color", color);
        caution = map.getString("caution", caution);

        Integer categoryId = map.getInt("category.id", category == null ? null : category.getId());
        if (categoryId != null) {
            if (category == null)
                category = new ArtifactCategory();
            category.setId(categoryId);
        }

        Integer phaseId = map.getInt("phase.id", phase == null ? null : phase.getId());
        if (phaseId != null) {
            if (phase == null)
                phase = new ArtifactPhase();
            phase.setId(phaseId);
        }
    }

}
