import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PlanDoTagTypeInfo from "./PlanDoTagTypeInfo";
import _PlanDoTag_stuff_Validators from "./_PlanDoTag_stuff_Validators";

export class PlanDoTagValidators extends _PlanDoTag_stuff_Validators {

    constructor(type: PlanDoTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanDoTagTypeInfo;
    }

}

export default PlanDoTagValidators;
