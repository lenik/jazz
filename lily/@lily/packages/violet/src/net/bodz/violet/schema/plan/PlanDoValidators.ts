import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PlanDoTypeInfo from "./PlanDoTypeInfo";
import _PlanDo_stuff_Validators from "./_PlanDo_stuff_Validators";

export class PlanDoValidators extends _PlanDo_stuff_Validators {

    constructor(type: PlanDoTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanDoTypeInfo;
    }

}

export default PlanDoValidators;
