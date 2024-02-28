import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";

import GeoLocation from "../../concrete/util/GeoLocation";
import ZoneTypeInfo from "./ZoneTypeInfo";
import _Zone_stuff from "./_Zone_stuff";

export class Zone extends _Zone_stuff {
    static _typeInfo: ZoneTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ZoneTypeInfo();
        return this._typeInfo;
    }


    fullPath?: string
    geo?: GeoLocation
    properties?: JsonVariant

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Zone;
