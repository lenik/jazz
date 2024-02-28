import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type VAppCat from "./VAppCat";
import type _VAppCat_stuff_TypeInfo from "./_VAppCat_stuff_TypeInfo";

export class _VAppCat_stuff_Validators extends CoEntityValidators {

    constructor(type: _VAppCat_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppCat_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateName(val: string) {
    }

    validateDepth(val: int) {
    }

    validateRefCount(val: int) {
    }

    validateParent(val: VAppCat) {
    }

}

export default _VAppCat_stuff_Validators;
