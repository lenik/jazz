import { ValidateResult } from "@skeljs/core/src/ui/types";

import AbstractDefinitionValidators from "./AbstractDefinitionValidators";
import type _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

export class _TagGroupDef_stuff_Validators extends AbstractDefinitionValidators {

    constructor(type: _TagGroupDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TagGroupDef_stuff_TypeInfo;
    }

    validateForTopic(val: boolean) {
    }

    validateForReply(val: boolean) {
    }

}

export default _TagGroupDef_stuff_Validators;
