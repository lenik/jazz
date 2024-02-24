import type { integer } from "@skeljs/core/src/lang/type";
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

    validateProperties(val: any) {
    }

    validateRoleCount(val: integer) {
    }

    validateBankCount(val: integer) {
    }

    validateSize(val: integer) {
    }

    validateTaxId(val: string) {
    }

}

export default _Organization_stuff_Validators;
