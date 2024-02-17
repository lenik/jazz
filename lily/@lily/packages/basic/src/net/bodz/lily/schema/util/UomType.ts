
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _Uom_stuff_Type } from "./_Uom_stuff_Type";

// Type Info

export class UomType extends _Uom_stuff_Type {

    name = "net.bodz.lily.schema.util.Uom"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UomType.declaredProperty);
    }

}
