import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FormDefTypeInfo from "./FormDefTypeInfo";
import _FormDef_stuff_Validators from "./_FormDef_stuff_Validators";

export class FormDefValidators extends _FormDef_stuff_Validators {

    constructor(type: FormDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FormDefTypeInfo;
    }

}

export default FormDefValidators;
