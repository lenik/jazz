import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PhaseDefValidators from "./PhaseDefValidators";
import _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class PhaseDefTypeInfo extends _PhaseDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.PhaseDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Phase"; }

    validators = new PhaseDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PhaseDefTypeInfo;
