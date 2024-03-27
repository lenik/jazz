import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class _UserType_stuff_Validators extends CoImagedValidators {

    constructor(type: _UserType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserType_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

    validateDummy(val: int) {
    }

}

export default _UserType_stuff_Validators;
