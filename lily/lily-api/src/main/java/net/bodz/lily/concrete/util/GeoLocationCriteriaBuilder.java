package net.bodz.lily.concrete.util;

import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * @see GeoLocation
 */
public class GeoLocationCriteriaBuilder
        implements IVarMapForm {

    GeoLocation refPoint;
    Double distance;

    DoubleRange longitudeRange;
    DoubleRange latitudeRange;
    Integer regionId;

    public GeoLocation getRefPoint() {
        return refPoint;
    }

    public void setRefPoint(GeoLocation refPoint) {
        this.refPoint = refPoint;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public DoubleRange getLongitudeRange() {
        return longitudeRange;
    }

    public void setLongitudeRange(DoubleRange longitudeRange) {
        this.longitudeRange = longitudeRange;
    }

    public DoubleRange getLatitudeRange() {
        return latitudeRange;
    }

    public void setLatitudeRange(DoubleRange latitudeRange) {
        this.latitudeRange = latitudeRange;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    /** ⇱ Implementation Of {@link IVarMapForm}. */
    /* _____________________________ */static section.iface __VarMap__;

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        distance = map.getDouble("distance");
        regionId = map.getInt("region.id");
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        if (distance != null)
            map.put("distance", distance);
        if (regionId != null)
            map.put("region.id", regionId);
    }

}
