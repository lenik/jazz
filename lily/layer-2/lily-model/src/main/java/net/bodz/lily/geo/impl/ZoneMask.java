package net.bodz.lily.geo.impl;

import net.bodz.lily.model.base.CoNodeMask;
import net.bodz.lily.model.mixin.GeoLocationMask;

/**
 * @see net.bodz.lily.geo.Zone
 */
public class ZoneMask
        extends CoNodeMask {

    private Integer categoryId;
    private final GeoLocationMask geo = new GeoLocationMask();

    public ZoneMask() {
        getIdRange().setStart(0L);
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public GeoLocationMask getGeo() {
        return geo;
    }

}
