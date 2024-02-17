
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Zone_stuff_Type } from "./_Zone_stuff_Type";

// Type Info

export class ZoneType extends _Zone_stuff_Type {

    name = "net.bodz.lily.schema.geo.Zone"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
        fullPath: property({ type: "string", validator: validators.validate_fullPath }),
        geo: property({ type: "net.bodz.lily.concrete.util.GeoLocation", validator: validators.validate_geo }),
        properties: property({ type: "net.bodz.lily.concrete.RichProperties", validator: validators.validate_properties }),
    }

    constructor() {
        super();
        this.declare(ZoneType.declaredProperty);
    }

}
