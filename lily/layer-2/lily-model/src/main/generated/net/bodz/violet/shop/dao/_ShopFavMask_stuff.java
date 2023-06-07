package net.bodz.violet.shop.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _ShopFavMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Integer shopId;
    IntegerRange shopIdRange = new IntegerRange();

    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer value) {
        this.shopId = value;
    }

    public IntegerRange getShopIdRange() {
        return shopIdRange;
    }

    public void setShopIdRange(IntegerRange range) {
        this.shopIdRange = range;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public IntegerRange getUserIdRange() {
        return userIdRange;
    }

    public void setUserIdRange(IntegerRange range) {
        this.userIdRange = range;
    }

}
