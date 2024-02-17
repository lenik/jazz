
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _VAppRequest_stuff_Type } from "./_VAppRequest_stuff_Type";

// Type Info

export class VAppRequestType extends _VAppRequest_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VAppRequest"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppRequestType.declaredProperty);
    }

}
