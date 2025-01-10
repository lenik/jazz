import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanTag from "./PlanTag";
import type PlanTypeInfo from "./PlanTypeInfo";
import _Plan_stuff_Validators from "./_Plan_stuff_Validators";

export class PlanValidators extends _Plan_stuff_Validators {

    constructor(type: PlanTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanTypeInfo;
    }

    validateTags(val: Set<PlanTag>) {
    }

}

export default PlanValidators;
