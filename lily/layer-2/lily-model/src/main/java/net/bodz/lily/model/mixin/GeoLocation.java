package net.bodz.lily.model.mixin;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.i18n.geo.GeoRegion;
import net.bodz.bas.i18n.geo.GeoRegions;

public class GeoLocation
        implements Serializable, IJsonSerializable {

    private static final long serialVersionUID = 1L;

    double longitude;
    double latitude;
    GeoRegion region;

    public double getSphereX() {
        return longitude;
    }

    public void setSphereX(double x) {
        longitude = x;
    }

    public double getSphereY() {
        return latitude;
    }

    public void setSphereY(double y) {
        latitude = y;
    }

    /**
     * Distance east or west of the Greenwichmeridian, measured in degrees.
     *
     * @label.zh_CN 经度
     */
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Distance of a place north or south of the equator, measured in degrees.
     *
     * @label.zh_CN 纬度
     */
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public GeoRegion getRegion() {
        return region;
    }

    public void setRegion(GeoRegion region) {
        this.region = region;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        longitude = o.getDouble("lng");
        latitude = o.getDouble("lat");
        String regionId = o.getString("id");
        if (regionId != null)
            region = GeoRegions.getChinaRegion(regionId);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.object();
        out.entry("lng", longitude);
        out.entry("lat", latitude);
        if (region != null)
            out.entry("id", region.getId());
        out.endObject();
    }

}
