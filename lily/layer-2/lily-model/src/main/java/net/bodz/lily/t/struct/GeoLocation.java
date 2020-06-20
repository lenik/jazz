package net.bodz.lily.t.struct;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.i18n.geo.GeoZone;
import net.bodz.bas.i18n.geo.GeoZones;

public class GeoLocation
        extends MixinStruct {

    private static final long serialVersionUID = 1L;

    double longitude;
    double latitude;
    GeoZone zone;

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

    public GeoZone getZone() {
        return zone;
    }

    public void setZone(GeoZone zone) {
        this.zone = zone;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        longitude = o.getDouble("lng");
        latitude = o.getDouble("lat");
        String code = o.getString("code");
        if (code != null)
            zone = GeoZones.getChinaRegion(code);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("lng", longitude);
        out.entry("lat", latitude);
        if (zone != null)
            out.entry("code", zone.getFullCode());
    }

}
