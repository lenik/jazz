import GeoLocation from "../../concrete/util/GeoLocation";
import ZoneTypeInfo from "./ZoneTypeInfo";
import _Zone_stuff from "./_Zone_stuff";

export class Zone extends _Zone_stuff {

    static _typeInfo: ZoneTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ZoneTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    fullPath?: string
    geo?: GeoLocation

    constructor(o?: any) {
        super(o);
    }
}

export default Zone;
