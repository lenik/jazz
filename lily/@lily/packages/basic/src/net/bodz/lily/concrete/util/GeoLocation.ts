import { double } from '@skeljs/core/src/lang/basetype';
import GeoZone from '@skeljs/core/src/net/bodz/bas/i18n/geo/GeoZone';
import GeoLocationTypeInfo from './GeoLocationTypeInfo';

export class GeoLocation {

    static readonly TYPE = new GeoLocationTypeInfo();

    longitude: double;
    latitude: double;
    geoZone: GeoZone | undefined;

}

export default GeoLocation;
