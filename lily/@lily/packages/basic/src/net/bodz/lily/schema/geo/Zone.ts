
import type { RichProperties } from "@skeljs/dba/src/net/bodz/lily/concrete/RichProperties";
import type { GeoLocation } from "@skeljs/dba/src/net/bodz/lily/concrete/util/GeoLocation";

import { * as validators } from "./PersonValidators";
import type { _Zone_stuff } from "./_Zone_stuff";

export class Zone extends _Zone_stuff {
    static TYPE = new ZoneType();

    fullPath?: string
    geo?: GeoLocation
    properties?: RichProperties

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
