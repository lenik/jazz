
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ExternalSite_stuff_Type } from "./_ExternalSite_stuff_Type";

// Type Info

export class ExternalSiteType extends _ExternalSite_stuff_Type {

    name = "net.bodz.lily.schema.inet.ExternalSite"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ExternalSiteType.declaredProperty);
    }

}
