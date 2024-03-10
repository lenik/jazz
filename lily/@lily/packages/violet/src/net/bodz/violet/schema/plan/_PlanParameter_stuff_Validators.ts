import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoParameterValidators from "@lily/basic/src/net/bodz/lily/concrete/CoParameterValidators";

import type _PlanParameter_stuff_TypeInfo from "./_PlanParameter_stuff_TypeInfo";

export class _PlanParameter_stuff_Validators extends CoParameterValidators {

    constructor(type: _PlanParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanParameter_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

    validateDummy(val: int) {
    }

}

export default _PlanParameter_stuff_Validators;
