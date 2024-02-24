import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type FormDef from "./FormDef";
import type _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class _FormParameter_stuff_Validators extends CoEntityValidators {

    constructor(type: _FormParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FormParameter_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateValue(val: string) {
    }

    validateForm(val: FormDef) {
    }

}

export default _FormParameter_stuff_Validators;
