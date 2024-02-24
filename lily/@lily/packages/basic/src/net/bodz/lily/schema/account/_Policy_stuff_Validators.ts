import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

export class _Policy_stuff_Validators extends CoEntityValidators {

    constructor(type: _Policy_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Policy_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateControlClass(val: string) {
    }

    validateMethodName(val: string) {
    }

    validateAllowBits(val: integer) {
    }

    validateDenyBits(val: integer) {
    }

}

export default _Policy_stuff_Validators;
