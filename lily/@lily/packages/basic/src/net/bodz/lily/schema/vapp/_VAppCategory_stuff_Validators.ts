import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoCategoryValidators from "../../concrete/CoCategoryValidators";
import type _VAppCategory_stuff_TypeInfo from "./_VAppCategory_stuff_TypeInfo";

export class _VAppCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _VAppCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _VAppCategory_stuff_Validators;
