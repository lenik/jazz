package net.bodz.lily.concrete.util;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.i18n.geo.GeoZone;
import net.bodz.bas.i18n.geo.GeoZones;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class GeoLocation
        extends MixinStruct
        implements
            IGeoLocation {

    Double longitude;
    Double latitude;
    GeoZone geoZone;

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public GeoZone getGeoZone() {
        return geoZone;
    }

    @Override
    public void setGeoZone(GeoZone zone) {
        this.geoZone = zone;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        longitude = o.getDouble("lng");
        latitude = o.getDouble("lat");
        String code = o.getString("code");
        if (code != null)
            geoZone = GeoZones.getChinaRegion(code);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("lng", longitude);
        out.entry("lat", latitude);
        if (geoZone != null)
            out.entry("code", geoZone.getFullCode());
    }

}
