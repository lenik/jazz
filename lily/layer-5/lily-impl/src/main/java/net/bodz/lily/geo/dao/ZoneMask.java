package net.bodz.lily.geo.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoNodeMask;
import net.bodz.lily.t.struct.GeoLocationMask;

/**
 * @see net.bodz.lily.geo.Zone
 */
public class ZoneMask
        extends CoNodeMask {

    IntegerRange idRange = new IntegerRange();

    private Integer categoryId;
    private final GeoLocationMask geo = new GeoLocationMask();

    public ZoneMask() {
    }

    public IntegerRange getIdRange() {
        return idRange;
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
