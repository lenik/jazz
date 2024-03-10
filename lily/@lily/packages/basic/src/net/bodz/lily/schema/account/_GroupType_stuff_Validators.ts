import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class _GroupType_stuff_Validators extends CoImagedValidators {

    constructor(type: _GroupType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _GroupType_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

    validateDummy(val: int) {
    }

}

export default _GroupType_stuff_Validators;
