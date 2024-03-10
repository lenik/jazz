import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoPhaseValidators from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseValidators";

import type _SalesPhase_stuff_TypeInfo from "./_SalesPhase_stuff_TypeInfo";

export class _SalesPhase_stuff_Validators extends CoPhaseValidators {

    constructor(type: _SalesPhase_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SalesPhase_stuff_TypeInfo;
    }

}

export default _SalesPhase_stuff_Validators;
