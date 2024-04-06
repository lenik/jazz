import { double } from '@skeljs/core/src/lang/basetype';
import GeoZone from '@skeljs/core/src/net/bodz/bas/i18n/geo/GeoZone';
import GeoLocationTypeInfo from './GeoLocationTypeInfo';

export class GeoLocation {

    static _typeInfo: GeoLocationTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = GeoLocationTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    longitude: double;
    latitude: double;
    geoZone: GeoZone | undefined;

}

export default GeoLocation;
