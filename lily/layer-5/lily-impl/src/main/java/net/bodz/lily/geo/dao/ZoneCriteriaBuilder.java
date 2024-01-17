package net.bodz.lily.geo.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoNodeCriteriaBuilder;
import net.bodz.lily.t.struct.GeoLocationCriteriaBuilder;

/**
 * @see net.bodz.lily.geo.Zone
 */
public class ZoneCriteriaBuilder
        extends CoNodeCriteriaBuilder {

    IntegerRange idRange = new IntegerRange();

    private Integer categoryId;
    private final GeoLocationCriteriaBuilder geo = new GeoLocationCriteriaBuilder();

    public ZoneCriteriaBuilder() {
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

    public GeoLocationCriteriaBuilder getGeo() {
        return geo;
    }

}
