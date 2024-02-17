
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _VApp_stuff_Type } from "./_VApp_stuff_Type";

// Type Info

export class VAppType extends _VApp_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VApp"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppType.declaredProperty);
    }

}
