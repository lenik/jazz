import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PhaseDefValidators from "./PhaseDefValidators";
import _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class PhaseDefTypeInfo extends _PhaseDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.PhaseDef"
    icon = "fa-tag"
    label = "Phase"

    validators = new PhaseDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PhaseDefTypeInfo;
