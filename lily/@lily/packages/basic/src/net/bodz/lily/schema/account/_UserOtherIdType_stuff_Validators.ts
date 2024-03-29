import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

export class _UserOtherIdType_stuff_Validators extends CoEntityValidators {

    constructor(type: _UserOtherIdType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserOtherIdType_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateDummy(val: int) {
    }

}

export default _UserOtherIdType_stuff_Validators;
