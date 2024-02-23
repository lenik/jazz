import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PhaseDefValidators from "./PhaseDefValidators";
import _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

// Type Info

export class PhaseDefTypeInfo extends _PhaseDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.PhaseDef"
    icon = "fa-tag"
    label = "Phase"

    static validators = new PhaseDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PhaseDefTypeInfo.declaredProperty);
    }

}

export default PhaseDef;
