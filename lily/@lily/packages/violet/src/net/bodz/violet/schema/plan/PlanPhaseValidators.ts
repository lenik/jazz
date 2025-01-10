import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanPhaseTypeInfo from "./PlanPhaseTypeInfo";
import _PlanPhase_stuff_Validators from "./_PlanPhase_stuff_Validators";

export class PlanPhaseValidators extends _PlanPhase_stuff_Validators {

    constructor(type: PlanPhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanPhaseTypeInfo;
    }

}

export default PlanPhaseValidators;
