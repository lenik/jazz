import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PlanParameterTypeTypeInfo from "./PlanParameterTypeTypeInfo";
import _PlanParameterType_stuff_Validators from "./_PlanParameterType_stuff_Validators";

export class PlanParameterTypeValidators extends _PlanParameterType_stuff_Validators {

    constructor(type: PlanParameterTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanParameterTypeTypeInfo;
    }

}

export default PlanParameterTypeValidators;
