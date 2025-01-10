import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

export class _Policy_stuff_Validators extends CoImagedValidators {

    constructor(type: _Policy_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Policy_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

    validateControlClass(val: string) {
    }

    validateMethodName(val: string) {
    }

    validateAllowBits(val: int) {
    }

    validateDenyBits(val: int) {
    }

}

export default _Policy_stuff_Validators;
