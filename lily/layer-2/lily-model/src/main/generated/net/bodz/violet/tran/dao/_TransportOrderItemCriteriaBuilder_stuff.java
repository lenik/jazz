package net.bodz.violet.tran.dao;

import java.math.BigDecimal;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;

public class _TransportOrderItemCriteriaBuilder_stuff
        extends CoMomentIntervalCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Long orderId;
    LongRange orderIdRange = new LongRange();

    Integer artifactId;
    IntegerRange artifactIdRange = new IntegerRange();

    BigDecimal quantity;
    BigDecimalRange quantityRange = new BigDecimalRange();

    BigDecimal price;
    BigDecimalRange priceRange = new BigDecimalRange();

    BigDecimal amount;
    BigDecimalRange amountRange = new BigDecimalRange();

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public LongRange getIdRange() {
        return idRange;
    }

    public void setIdRange(LongRange range) {
        this.idRange = range;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer value) {
        this.priority = value;
    }

    public IntegerRange getPriorityRange() {
        return priorityRange;
    }

    public void setPriorityRange(IntegerRange range) {
        this.priorityRange = range;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long value) {
        this.orderId = value;
    }

    public LongRange getOrderIdRange() {
        return orderIdRange;
    }

    public void setOrderIdRange(LongRange range) {
        this.orderIdRange = range;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer value) {
        this.artifactId = value;
    }

    public IntegerRange getArtifactIdRange() {
        return artifactIdRange;
    }

    public void setArtifactIdRange(IntegerRange range) {
        this.artifactIdRange = range;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal value) {
        this.quantity = value;
    }

    public BigDecimalRange getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(BigDecimalRange range) {
        this.quantityRange = range;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    public BigDecimalRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimalRange range) {
        this.priceRange = range;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    public BigDecimalRange getAmountRange() {
        return amountRange;
    }

    public void setAmountRange(BigDecimalRange range) {
        this.amountRange = range;
    }

}
