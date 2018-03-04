package net.bodz.violet.art;

import javax.persistence.Table;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.repr.EntGroup;

/**
 * 物品装箱
 */
@IdType(Integer.class)
// @SchemaPref(Schemas.ARTIFACT)
@Table(name = "artpack")
public class Packaging
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private Artifact artifact;
    private Dim3d bbox = new Dim3d();
    private double weight;
    private double netWeight;

    private Artifact content;
    private double quantity = 0.0;

    /**
     * 成箱物品
     */
    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
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
     * 装箱内的物品
     */
    public Artifact getContent() {
        return content;
    }

    public void setContent(Artifact content) {
        this.content = content;
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

}
