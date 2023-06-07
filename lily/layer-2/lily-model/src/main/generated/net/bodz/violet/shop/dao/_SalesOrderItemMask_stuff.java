package net.bodz.violet.shop.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _SalesOrderItemMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp beginTime;
    DateTimeRange beginTimeRange = new DateTimeRange();

    Timestamp endTime;
    DateTimeRange endTimeRange = new DateTimeRange();

    Integer year;
    IntegerRange yearRange = new IntegerRange();

    Object properties;

    Long odrId;
    LongRange odrIdRange = new LongRange();

    Long shopItemId;
    LongRange shopItemIdRange = new LongRange();

    Integer artifactId;
    IntegerRange artifactIdRange = new IntegerRange();

    Object batch;

    BigDecimal quantity;
    BigDecimalRange quantityRange = new BigDecimalRange();

    BigDecimal price;
    BigDecimalRange priceRange = new BigDecimalRange();

    BigDecimal amount;
    BigDecimalRange amountRange = new BigDecimalRange();

    BigDecimal n1;
    BigDecimalRange n1Range = new BigDecimalRange();

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

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp value) {
        this.beginTime = value;
    }

    public DateTimeRange getBeginTimeRange() {
        return beginTimeRange;
    }

    public void setBeginTimeRange(DateTimeRange range) {
        this.beginTimeRange = range;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp value) {
        this.endTime = value;
    }

    public DateTimeRange getEndTimeRange() {
        return endTimeRange;
    }

    public void setEndTimeRange(DateTimeRange range) {
        this.endTimeRange = range;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer value) {
        this.year = value;
    }

    public IntegerRange getYearRange() {
        return yearRange;
    }

    public void setYearRange(IntegerRange range) {
        this.yearRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Long getOdrId() {
        return odrId;
    }

    public void setOdrId(Long value) {
        this.odrId = value;
    }

    public LongRange getOdrIdRange() {
        return odrIdRange;
    }

    public void setOdrIdRange(LongRange range) {
        this.odrIdRange = range;
    }

    public Long getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(Long value) {
        this.shopItemId = value;
    }

    public LongRange getShopItemIdRange() {
        return shopItemIdRange;
    }

    public void setShopItemIdRange(LongRange range) {
        this.shopItemIdRange = range;
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

    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
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

    public BigDecimal getN1() {
        return n1;
    }

    public void setN1(BigDecimal value) {
        this.n1 = value;
    }

    public BigDecimalRange getN1Range() {
        return n1Range;
    }

    public void setN1Range(BigDecimalRange range) {
        this.n1Range = range;
    }

}
