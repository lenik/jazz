import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ParameterDef from "./ParameterDef";
import type _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class _ParameterValue_stuff_Validators extends CoEntityValidators {

    constructor(type: _ParameterValue_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ParameterValue_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateVal(val: string) {
    }

    validateParameter(val: ParameterDef) {
    }

}

export default _ParameterValue_stuff_Validators;
