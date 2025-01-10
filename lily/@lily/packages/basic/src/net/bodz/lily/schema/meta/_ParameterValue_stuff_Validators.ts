import { ValidateResult } from "skel01-core/src/ui/types";

import AbstractDefinitionValidators from "./AbstractDefinitionValidators";
import type ParameterDef from "./ParameterDef";
import type _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class _ParameterValue_stuff_Validators extends AbstractDefinitionValidators {

    constructor(type: _ParameterValue_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ParameterValue_stuff_TypeInfo;
    }

    validateVal(val: string) {
    }

    validateParameter(val: ParameterDef) {
    }

}

export default _ParameterValue_stuff_Validators;
