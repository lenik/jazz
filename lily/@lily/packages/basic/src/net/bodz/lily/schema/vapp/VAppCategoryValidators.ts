import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type VAppCategoryTypeInfo from "./VAppCategoryTypeInfo";
import _VAppCategory_stuff_Validators from "./_VAppCategory_stuff_Validators";

export class VAppCategoryValidators extends _VAppCategory_stuff_Validators {

    constructor(type: VAppCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VAppCategoryTypeInfo;
    }

}

export default VAppCategoryValidators;