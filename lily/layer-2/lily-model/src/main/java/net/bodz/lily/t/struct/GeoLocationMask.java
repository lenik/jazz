package net.bodz.lily.t.struct;

import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * @see GeoLocation
 */
public class GeoLocationMask
        implements IVarMapSerializable {

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

    /** ⇱ Implementation Of {@link IVarMapSerializable}. */
    /* _____________________________ */static section.iface __VarMap__;

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException {
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
