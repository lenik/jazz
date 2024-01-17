package net.bodz.violet.store.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;

public class _StoreOrderItemCriteriaBuilder_stuff
        extends CoMomentIntervalCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Long orderId;
    LongRange orderIdRange = new LongRange();

    Integer artifactId;
    IntegerRange artifactIdRange = new IntegerRange();

    Integer regionId;
    IntegerRange regionIdRange = new IntegerRange();

    Object batch;

    Long serial;
    LongRange serialRange = new LongRange();

    Timestamp expire;
    DateTimeRange expireRange = new DateTimeRange();

    BigDecimal quantity;
    BigDecimalRange quantityRange = new BigDecimalRange();

    BigDecimal price;
    BigDecimalRange priceRange = new BigDecimalRange();

    BigDecimal amount;
    BigDecimalRange amountRange = new BigDecimalRange();

    String notes;
    String notesPattern;

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer value) {
        this.regionId = value;
    }

    public IntegerRange getRegionIdRange() {
        return regionIdRange;
    }

    public void setRegionIdRange(IntegerRange range) {
        this.regionIdRange = range;
    }

    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long value) {
        this.serial = value;
    }

    public LongRange getSerialRange() {
        return serialRange;
    }

    public void setSerialRange(LongRange range) {
        this.serialRange = range;
    }

    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp value) {
        this.expire = value;
    }

    public DateTimeRange getExpireRange() {
        return expireRange;
    }

    public void setExpireRange(DateTimeRange range) {
        this.expireRange = range;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String value) {
        this.notes = value;
    }

    public String getNotesPattern() {
        return notesPattern;
    }

    public void setNotesPattern(String value) {
        this.notesPattern = value;
    }

}
