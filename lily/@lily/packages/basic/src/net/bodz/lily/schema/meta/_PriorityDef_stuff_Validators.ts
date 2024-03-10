import { ValidateResult } from "@skeljs/core/src/ui/types";

import AbstractDefinitionValidators from "./AbstractDefinitionValidators";
import type _PriorityDef_stuff_TypeInfo from "./_PriorityDef_stuff_TypeInfo";

export class _PriorityDef_stuff_Validators extends AbstractDefinitionValidators {

    constructor(type: _PriorityDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PriorityDef_stuff_TypeInfo;
    }

}

export default _PriorityDef_stuff_Validators;
