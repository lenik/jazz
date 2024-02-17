
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PhaseDef_stuff_Type } from "./_PhaseDef_stuff_Type";

// Type Info

export class PhaseDefType extends _PhaseDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.PhaseDef"
    icon = "fa-tag"
    label = "Phase"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PhaseDefType.declaredProperty);
    }

}
