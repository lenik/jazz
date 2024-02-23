import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PhaseDefValidators from "./PhaseDefValidators";
import _PhaseDef_stuff_Type from "./_PhaseDef_stuff_Type";

// Type Info

export class PhaseDefType extends _PhaseDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.PhaseDef"
    icon = "fa-tag"
    label = "Phase"

    static validators = new PhaseDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PhaseDefType.declaredProperty);
    }

}

export default PhaseDef;
