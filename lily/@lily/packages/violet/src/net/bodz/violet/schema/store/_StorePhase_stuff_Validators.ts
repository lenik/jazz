import { ValidateResult } from "skel01-core/src/ui/types";
import CoPhaseValidators from "lily-basic/src/net/bodz/lily/concrete/CoPhaseValidators";

import type _StorePhase_stuff_TypeInfo from "./_StorePhase_stuff_TypeInfo";

export class _StorePhase_stuff_Validators extends CoPhaseValidators {

    constructor(type: _StorePhase_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _StorePhase_stuff_TypeInfo;
    }

}

export default _StorePhase_stuff_Validators;
