import type { double, int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type PlanCategory from "./PlanCategory";
import type PlanPhase from "./PlanPhase";
import type _Plan_stuff_TypeInfo from "./_Plan_stuff_TypeInfo";

export class _Plan_stuff_Validators extends CoMessageValidators {

    constructor(type: _Plan_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Plan_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateReadCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateNlike(val: int) {
    }

    validateValue(val: double) {
    }

    validateCategory(val: PlanCategory) {
    }

    validatePhase(val: PlanPhase) {
    }

}

export default _Plan_stuff_Validators;
