import { double } from '@skeljs/core/src/lang/basetype';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import GeoLocation from "./GeoLocation";
import GeoZone from '@skeljs/core/src/net/bodz/bas/i18n/geo/GeoZone';

export class GeoLocationTypeInfo extends TypeInfo<GeoLocation> {

    override get name() { return 'GeoLocation' }
    override get icon() { return 'fa-location' }

    create(): GeoLocation {
        return new GeoLocation();
    }

    override parse(s: string): GeoLocation {
        let [lng, lat, geo] = s.split(',');
        let a = new GeoLocation();
        a.longitude = parseFloat(lng);
        a.latitude = parseFloat(lat);
        if (geo != '') {
            a.geoZone = GeoZone.TYPE.parse(geo);
        }
        return a;
    }

    override format(val: GeoLocation): string {
        let { longitude, latitude, geoZone } = val;
        let s = `${longitude},${latitude}`;
        if (geoZone != null) {
            let gz = GeoZone.TYPE.format(geoZone);
            s += `,${gz}`;
        }
        return s;
    }

    override fromJson(jv: any): GeoLocation {
        let a = new GeoLocation();
        a.longitude = jv.longitude;
        a.latitude = jv.latitude;
        if (jv.geoZone != null)
            a.geoZone = GeoZone.TYPE.fromJson(jv.geoZone);
        return a;
    }

    override toJson(val: GeoLocation) {
        let jo: any = {
            longitude: val.longitude,
            latitude: val.latitude,
        };
        if (val.geoZone != null)
            jo.geoZone = GeoZone.TYPE.toJson(val.geoZone);
        return jo;
    }

    static readonly INSTANCE = new GeoLocationTypeInfo();

}

export default GeoLocationTypeInfo;
export const GeoLocation_TYPE = GeoLocationTypeInfo.INSTANCE;
