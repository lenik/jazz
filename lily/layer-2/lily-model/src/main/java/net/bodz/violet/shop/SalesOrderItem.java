package net.bodz.violet.shop;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.store.Artifact;

/**
 * 订单项
 */
@IdType(Long.class)
@Table(name = "saleodrl")
public class SalesOrderItem
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_ALT_LABEL = 30;
    public static final int N_ALT_SPEC = 80;
    public static final int N_COMMENT1 = 200;
    public static final int N_FOOTNOTE = 200;

    SalesOrder order;
    ShopItem shopItem;
    Artifact artifact;

    boolean resale;
    String altLabel;
    String altSpec;
    String altUom;

    double quantity;
    double price;
    String footnote;

    /**
     * 订单
     */
    @Priority(1)
    public SalesOrder getOrder() {
        return order;
    }

    public void setOrder(SalesOrder order) {
        this.order = order;
    }

    /**
     * 上架跟踪
     */
    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    /**
     * 商品
     */
    @Priority(100)
    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public DateTime getBeginDate() {
        return super.getBeginDate();
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public DateTime getEndDate() {
        return super.getEndDate();
    }

    /**
     * 订单时间
     */
    @OfGroup(StdGroup.Schedule.class)
    public DateTime getOrderTime() {
        return super.getBeginDate();
    }

    public void setOrderTime(DateTime orderTime) {
        super.setBeginDate(orderTime);
    }

    /**
     * 交货时间
     */
    @OfGroup(StdGroup.Schedule.class)
    public DateTime getDeadline() {
        return super.getEndDate();
    }

    public void setDeadline(DateTime deadline) {
        super.setEndDate(deadline);
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
    @Priority(200)
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * 价格
     */
    @Priority(201)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 总额
     */
    @Priority(202)
    @Derived
    public double getTotal() {
        return price * quantity;
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
    @TextInput(maxLength = N_FOOTNOTE)
    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }

}