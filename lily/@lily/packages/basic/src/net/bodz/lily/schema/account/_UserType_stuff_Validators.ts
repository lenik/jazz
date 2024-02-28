import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class _UserType_stuff_Validators extends CoEntityValidators {

    constructor(type: _UserType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserType_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateName(val: string) {
    }

    validateDummy(val: int) {
    }

}

export default _UserType_stuff_Validators;
