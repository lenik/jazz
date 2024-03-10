import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoCategoryValidators from "../../concrete/CoCategoryValidators";
import type _VAppCat_stuff_TypeInfo from "./_VAppCat_stuff_TypeInfo";

export class _VAppCat_stuff_Validators extends CoCategoryValidators {

    constructor(type: _VAppCat_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppCat_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _VAppCat_stuff_Validators;
