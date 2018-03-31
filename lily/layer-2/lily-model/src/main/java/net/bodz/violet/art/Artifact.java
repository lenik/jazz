package net.bodz.violet.art;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.site.json.JsonVarMap;
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
        extends CoEntity<Integer>
        implements IAttributed {

    private static final long serialVersionUID = 1L;

    public static final int N_SKU_CODE = 30;
    public static final int N_BAR_CODE = 30;
    public static final int N_UOM_PROPERTY = 20;
    public static final int N_MODEL_NAME = 100;

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

    private ArtifactProperties properties = createProperties();

    // cache
    private BigDecimal price = BigDecimal.ZERO;

    protected ArtifactProperties createProperties() {
        return new ArtifactProperties();
    }

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

    @Override
    public <T> T getAttribute(String name) {
        return properties.getAttribute(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return properties.getAttribute(name, defaultValue);
    }

    @Override
    public void setAttribute(String name, Object value) {
        properties.setAttribute(name, value);
    }

    public ArtifactProperties getProperties() {
        return properties;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException {
        super.readObject(map);
        JsonVarMap properties = (JsonVarMap) map.get("properties");
        if (properties != null)
            try {
                this.properties.readObject(properties.getWrapped());
            } catch (ParseException e) {
                throw new LoaderException(e.getMessage(), e);
            }
    }

}
