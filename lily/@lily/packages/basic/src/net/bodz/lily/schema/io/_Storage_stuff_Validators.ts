import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class _Storage_stuff_Validators extends CoEntityValidators {

    constructor(type: _Storage_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Storage_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateName(val: string) {
    }

}

export default _Storage_stuff_Validators;
