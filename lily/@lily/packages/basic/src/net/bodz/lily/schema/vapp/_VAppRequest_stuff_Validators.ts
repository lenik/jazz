import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoMessageValidators from "../../concrete/CoMessageValidators";
import type _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class _VAppRequest_stuff_Validators extends CoMessageValidators {

    constructor(type: _VAppRequest_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppRequest_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateFormArguments(val: string) {
    }

    validateDummy(val: int) {
    }

}

export default _VAppRequest_stuff_Validators;
