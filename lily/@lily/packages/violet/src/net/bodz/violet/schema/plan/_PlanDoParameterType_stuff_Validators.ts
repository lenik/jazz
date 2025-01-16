import { ValidateResult } from "skel01-core/src/ui/types";
import CoParameterValidators from "lily-basic/src/net/bodz/lily/concrete/CoParameterValidators";

import type _PlanDoParameterType_stuff_TypeInfo from "./_PlanDoParameterType_stuff_TypeInfo";

export class _PlanDoParameterType_stuff_Validators extends CoParameterValidators {

    constructor(type: _PlanDoParameterType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanDoParameterType_stuff_TypeInfo;
    }

}

export default _PlanDoParameterType_stuff_Validators;
