
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ParameterDef_stuff_Type } from "./_ParameterDef_stuff_Type";

// Type Info

export class ParameterDefType extends _ParameterDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.ParameterDef"
    icon = "fa-tag"
    label = "Parameter"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ParameterDefType.declaredProperty);
    }

}
