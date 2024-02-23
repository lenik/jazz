import { GeoLocation } from "@skeljs/dba/src/net/bodz/lily/concrete/util/GeoLocation";

import _Zone_stuff from "./_Zone_stuff";
import { _Zone_stuffTypeInfo } from "./_Zone_stuffTypeInfo";

export class Zone extends _Zone_stuff {
    static TYPE = new _Zone_stuffTypeInfo();

    fullPath?: string
    geo?: GeoLocation
    properties?: any

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Zone;
