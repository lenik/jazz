import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FormParameterTypeInfo from "./FormParameterTypeInfo";
import _FormParameter_stuff_Validators from "./_FormParameter_stuff_Validators";

export class FormParameterValidators extends _FormParameter_stuff_Validators {

    constructor(type: FormParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FormParameterTypeInfo;
    }

}

export default FormParameterValidators;
