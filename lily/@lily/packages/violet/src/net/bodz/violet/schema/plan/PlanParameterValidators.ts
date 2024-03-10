import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PlanParameterTypeInfo from "./PlanParameterTypeInfo";
import _PlanParameter_stuff_Validators from "./_PlanParameter_stuff_Validators";

export class PlanParameterValidators extends _PlanParameter_stuff_Validators {

    constructor(type: PlanParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanParameterTypeInfo;
    }

}

export default PlanParameterValidators;
