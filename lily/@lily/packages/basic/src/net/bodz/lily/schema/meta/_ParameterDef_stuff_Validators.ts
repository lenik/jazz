import { ValidateResult } from "skel01-core/src/ui/types";

import AbstractDefinitionValidators from "./AbstractDefinitionValidators";
import type _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class _ParameterDef_stuff_Validators extends AbstractDefinitionValidators {

    constructor(type: _ParameterDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ParameterDef_stuff_TypeInfo;
    }

}

export default _ParameterDef_stuff_Validators;
