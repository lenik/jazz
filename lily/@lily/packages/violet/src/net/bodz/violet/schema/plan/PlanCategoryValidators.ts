import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanCategoryTypeInfo from "./PlanCategoryTypeInfo";
import _PlanCategory_stuff_Validators from "./_PlanCategory_stuff_Validators";

export class PlanCategoryValidators extends _PlanCategory_stuff_Validators {

    constructor(type: PlanCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanCategoryTypeInfo;
    }

}

export default PlanCategoryValidators;
