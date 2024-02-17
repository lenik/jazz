
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _VApi_stuff_Type } from "./_VApi_stuff_Type";

// Type Info

export class VApiType extends _VApi_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VApi"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiType.declaredProperty);
    }

}
