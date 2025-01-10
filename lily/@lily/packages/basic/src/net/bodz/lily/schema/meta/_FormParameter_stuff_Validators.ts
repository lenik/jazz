import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type FormDef from "./FormDef";
import type _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class _FormParameter_stuff_Validators extends IdEntityValidators {

    constructor(type: _FormParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FormParameter_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

    validateValue(val: string) {
    }

    validateForm(val: FormDef) {
    }

}

export default _FormParameter_stuff_Validators;
