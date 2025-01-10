import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

export class _UserOtherIdType_stuff_Validators extends CoImagedValidators {

    constructor(type: _UserOtherIdType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserOtherIdType_stuff_TypeInfo;
    }

    validateDummy(val: int) {
    }

}

export default _UserOtherIdType_stuff_Validators;
