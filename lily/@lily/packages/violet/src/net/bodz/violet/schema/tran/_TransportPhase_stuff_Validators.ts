import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoPhaseValidators from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseValidators";

import type _TransportPhase_stuff_TypeInfo from "./_TransportPhase_stuff_TypeInfo";

export class _TransportPhase_stuff_Validators extends CoPhaseValidators {

    constructor(type: _TransportPhase_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TransportPhase_stuff_TypeInfo;
    }

}

export default _TransportPhase_stuff_Validators;
