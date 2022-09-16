package net.bodz.violet.store;

import java.math.BigDecimal;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.t.struct.IOrderItem;
import net.bodz.violet.art.Artifact;

/**
 * 库存操作项目
 */
@IdType(Long.class)
@Table(name = "storeodrl")
public class StoreOrderItem
        extends CoMomentInterval<Long>
        implements IOrderItem {

    private static final long serialVersionUID = 1L;

    public static final int N_BATCH = 30;
    public static final int N_DIVS = 100;

    StoreOrder order;

    Artifact artifact;
    Region region;

    String batch;
    String divs;
    Long serial;
    DateTime expire;

    BigDecimal quantity = BigDecimal.ZERO;
    BigDecimal price = BigDecimal.ZERO;
    BigDecimal amount;

    String notes;

    @Override
    public void reset() {
        setAccessMode(M_SHARED);
    }

    public StoreOrder getOrder() {
        return order;
    }

    public void setOrder(StoreOrder order) {
        this.order = order;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDivs() {
        return divs;
    }

    public void setDivs(String divs) {
        this.divs = divs;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public DateTime getExpire() {
        return expire;
    }

    public void setExpire(DateTime expire) {
        this.expire = expire;
    }

    /**
     * 数量
     */
    @Priority(200)
    @Override
    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public synchronized void setQuantity(BigDecimal quantity) {
        if (quantity == null)
            throw new NullPointerException("quantity");
        this.quantity = quantity;
        this.amount = null;
    }

    @Override
    public void setQuantity(double quantity) {
        setQuantity(BigDecimal.valueOf(quantity));
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格
     */
    @Priority(201)
    @Override
    public synchronized void setPrice(BigDecimal price) {
        if (price == null)
            throw new NullPointerException("price");
        this.price = price;
        this.amount = null;
    }

    public void setPrice(double price) {
        setPrice(BigDecimal.valueOf(price));
    }

    /**
     * 总额
     */
    @Priority(202)
    @Derived
    @Override
    public synchronized BigDecimal getAmount() {
        if (amount == null)
            amount = price.multiply(quantity);
        return amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}