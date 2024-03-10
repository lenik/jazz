import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PlanDoParameterTypeInfo from "./PlanDoParameterTypeInfo";
import _PlanDoParameter_stuff_Validators from "./_PlanDoParameter_stuff_Validators";

export class PlanDoParameterValidators extends _PlanDoParameter_stuff_Validators {

    constructor(type: PlanDoParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanDoParameterTypeInfo;
    }

}

export default PlanDoParameterValidators;
