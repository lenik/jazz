import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class _GroupType_stuff_Validators extends CoEntityValidators {

    constructor(type: _GroupType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _GroupType_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateDummy(val: integer) {
    }

}

export default _GroupType_stuff_Validators;
