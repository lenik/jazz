package net.bodz.violet.shop.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.shop.Shop
 */
public class ShopCriteriaBuilder
        extends CoObjectCriteriaBuilder {

    String hydm;

    public String getHydm() {
        return hydm;
    }

    public void setHydm(String hydm) {
        this.hydm = hydm;
    }

}
