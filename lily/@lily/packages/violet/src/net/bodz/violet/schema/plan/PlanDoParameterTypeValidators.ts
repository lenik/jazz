import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanDoParameterTypeTypeInfo from "./PlanDoParameterTypeTypeInfo";
import _PlanDoParameterType_stuff_Validators from "./_PlanDoParameterType_stuff_Validators";

export class PlanDoParameterTypeValidators extends _PlanDoParameterType_stuff_Validators {

    constructor(type: PlanDoParameterTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanDoParameterTypeTypeInfo;
    }

}

export default PlanDoParameterTypeValidators;
