import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import PartyValidators from "./PartyValidators";
import type _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class _Organization_stuff_Validators extends PartyValidators {

    constructor(type: _Organization_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Organization_stuff_TypeInfo;
    }

    validateProperties(val: JsonVariant) {
    }

    validateRoleCount(val: int) {
    }

    validateBankCount(val: int) {
    }

    validateSize(val: int) {
    }

    validateTaxId(val: string) {
    }

}

export default _Organization_stuff_Validators;
