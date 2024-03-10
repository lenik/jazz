import { ValidateResult } from "@skeljs/core/src/ui/types";

import type StorePhaseTypeInfo from "./StorePhaseTypeInfo";
import _StorePhase_stuff_Validators from "./_StorePhase_stuff_Validators";

export class StorePhaseValidators extends _StorePhase_stuff_Validators {

    constructor(type: StorePhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StorePhaseTypeInfo;
    }

}

export default StorePhaseValidators;
