
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _VAppCat_stuff_Type } from "./_VAppCat_stuff_Type";

// Type Info

export class VAppCatType extends _VAppCat_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VAppCat"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppCatType.declaredProperty);
    }

}
