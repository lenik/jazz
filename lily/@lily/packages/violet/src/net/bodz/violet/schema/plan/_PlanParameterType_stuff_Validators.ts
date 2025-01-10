import { ValidateResult } from "skel01-core/src/ui/types";
import CoParameterValidators from "@lily/basic/src/net/bodz/lily/concrete/CoParameterValidators";

import type _PlanParameterType_stuff_TypeInfo from "./_PlanParameterType_stuff_TypeInfo";

export class _PlanParameterType_stuff_Validators extends CoParameterValidators {

    constructor(type: _PlanParameterType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanParameterType_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _PlanParameterType_stuff_Validators;
