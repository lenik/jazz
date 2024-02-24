import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoParameterValidators from "../../concrete/CoParameterValidators";
import type _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

export class _PostParameterType_stuff_Validators extends CoParameterValidators {

    constructor(type: _PostParameterType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostParameterType_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

    validateDummy(val: integer) {
    }

}

export default _PostParameterType_stuff_Validators;
