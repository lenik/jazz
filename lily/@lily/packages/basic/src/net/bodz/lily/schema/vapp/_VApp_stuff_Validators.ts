import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type VAppCat from "./VAppCat";
import type VAppRequest from "./VAppRequest";
import type _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class _VApp_stuff_Validators extends CoEntityValidators {

    constructor(type: _VApp_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApp_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateCode(val: string) {
    }

    validateSecret(val: string) {
    }

    validateCategory(val: VAppCat) {
    }

    validateReq(val: VAppRequest) {
    }

}

export default _VApp_stuff_Validators;
