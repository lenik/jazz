package net.bodz.violet.shop.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _ShopItemFavMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Long shopItemId;
    LongRange shopItemIdRange = new LongRange();

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
