
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ParameterValue_stuff_Type } from "./_ParameterValue_stuff_Type";

// Type Info

export class ParameterValueType extends _ParameterValue_stuff_Type {

    name = "net.bodz.lily.schema.meta.ParameterValue"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ParameterValueType.declaredProperty);
    }

}
