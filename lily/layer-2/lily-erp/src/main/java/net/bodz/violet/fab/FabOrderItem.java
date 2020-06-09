package net.bodz.violet.fab;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.model.mixin.IOrderItem;
import net.bodz.violet.art.Artifact;

@Table(name = "fabodrl")
@IdType(Long.class)
public class FabOrderItem
        extends CoMomentInterval<Long>
        implements IOrderItem {

    private static final long serialVersionUID = 1L;

    public static final int N_ALT_LABEL = 30;
    public static final int N_ALT_SPEC = 80;
    public static final int N_NOTES = 200;

    FabOrder order;
    Artifact artifact;

    boolean resale;
    String altLabel;
    String altSpec;
    String altUom;

    BigDecimal quantity = BigDecimal.ZERO;
    BigDecimal price = BigDecimal.ZERO;
    BigDecimal amount;
    String notes;

    public FabOrderItem() {
    }

    public FabOrder getOrder() {
        return order;
    }

    public void setOrder(FabOrder order) {
        this.order = order;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 定制铭牌
     */
    @Priority(1)
    @OfGroup(StdGroup.Option.class)
    public boolean isResale() {
        return resale;
    }

    public void setResale(boolean resale) {
        this.resale = resale;
    }

    /**
     * 定制名称
     */
    @Priority(2)
    @OfGroup(StdGroup.Option.class)
    @TextInput(maxLength = N_ALT_LABEL)
    public String getAltLabel() {
        return altLabel;
    }

    public void setAltLabel(String altLabel) {
        this.altLabel = altLabel;
    }

    /**
     * 定制规格
     */
    @Priority(3)
    @OfGroup(StdGroup.Option.class)
    @TextInput(maxLength = N_ALT_SPEC)
    public String getAltSpec() {
        return altSpec;
    }

    public void setAltSpec(String altSpec) {
        this.altSpec = altSpec;
    }

    /**
     * 数量
     */
    @Override
    @Priority(200)
    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public synchronized void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        this.amount = null;
    }

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    /**
     * 价格
     */
    @Override
    @Priority(201)
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public synchronized void setPrice(BigDecimal price) {
        this.price = price;
        this.amount = null;
    }

    @Override
    public void setPrice(double price) {
        setPrice(BigDecimal.valueOf(price));
    }

    /**
     * 总额
     */
    @Derived
    @Override
    @Priority(202)
    public synchronized BigDecimal getAmount() {
        if (amount == null)
            amount = price.multiply(quantity);
        return amount;
    }

    /**
     * @label Comment
     * @label.zh 注释
     * @placeholder 输入注释…
     */
    @Priority(800)
    @TextInput(maxLength = 200)
    @Override
    public String getComment() {
        return super.getComment();
    }

    /**
     * 注释/附加
     */
    @Priority(801)
    @TextInput(maxLength = N_NOTES)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
