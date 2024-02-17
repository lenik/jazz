
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _VApiLog_stuff_Type } from "./_VApiLog_stuff_Type";

// Type Info

export class VApiLogType extends _VApiLog_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VApiLog"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiLogType.declaredProperty);
    }

}
