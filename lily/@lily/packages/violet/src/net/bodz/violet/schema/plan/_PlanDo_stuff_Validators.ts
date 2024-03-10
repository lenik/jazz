import type { int, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type Plan from "./Plan";
import type PlanDo from "./PlanDo";
import type _PlanDo_stuff_TypeInfo from "./_PlanDo_stuff_TypeInfo";

export class _PlanDo_stuff_Validators extends CoMessageValidators {

    constructor(type: _PlanDo_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanDo_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateVoteCount(val: int) {
    }

    validateChanges(val: string[]) {
    }

    validatePlan(val: Plan) {
    }

    validateParent(val: PlanDo) {
    }

}

export default _PlanDo_stuff_Validators;
