import GeoLocation from "../../concrete/util/GeoLocation";
import ZoneTypeInfo from "./ZoneTypeInfo";
import _Zone_stuff from "./_Zone_stuff";

export class Zone extends _Zone_stuff {
    static TYPE = new ZoneTypeInfo();

    fullPath?: string
    geo?: GeoLocation
    properties?: any

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Zone;
