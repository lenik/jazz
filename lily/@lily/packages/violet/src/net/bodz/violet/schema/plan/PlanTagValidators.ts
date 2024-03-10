import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PlanTagTypeInfo from "./PlanTagTypeInfo";
import _PlanTag_stuff_Validators from "./_PlanTag_stuff_Validators";

export class PlanTagValidators extends _PlanTag_stuff_Validators {

    constructor(type: PlanTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanTagTypeInfo;
    }

}

export default PlanTagValidators;
