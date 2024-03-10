import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class _ApiType_stuff_Validators extends CoImagedValidators {

    constructor(type: _ApiType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ApiType_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateUom(val: string) {
    }

}

export default _ApiType_stuff_Validators;
