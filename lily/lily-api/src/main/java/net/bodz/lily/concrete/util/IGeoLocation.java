package net.bodz.lily.concrete.util;

import net.bodz.bas.i18n.geo.GeoZone;

public interface IGeoLocation {

    default double getSphereX() {
        return getLongitude();
    }

    default void setSphereX(double x) {
        setLongitude(x);
    }

    default double getSphereY() {
        return getLatitude();
    }

    default void setSphereY(double y) {
        setLatitude(y);
    }

    /**
     * Distance east or west of the Greenwichmeridian, measured in degrees.
     *
     * @label.zh_CN 经度
     */
    Double getLongitude();

    void setLongitude(Double longitude);

    /**
     * Distance of a place north or south of the equator, measured in degrees.
     *
     * @label.zh_CN 纬度
     */
    Double getLatitude();

    void setLatitude(Double latitude);

    GeoZone getGeoZone();

    void setGeoZone(GeoZone geoZone);

}
