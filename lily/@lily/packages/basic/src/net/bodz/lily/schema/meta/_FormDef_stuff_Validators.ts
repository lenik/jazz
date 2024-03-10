import { ValidateResult } from "@skeljs/core/src/ui/types";

import AbstractDefinitionValidators from "./AbstractDefinitionValidators";
import type _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class _FormDef_stuff_Validators extends AbstractDefinitionValidators {

    constructor(type: _FormDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FormDef_stuff_TypeInfo;
    }

    validateSubject(val: string) {
    }

    validateRawText(val: string) {
    }

}

export default _FormDef_stuff_Validators;
