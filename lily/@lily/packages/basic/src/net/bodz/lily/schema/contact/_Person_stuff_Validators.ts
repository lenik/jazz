import type { char, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import PartyValidators from "./PartyValidators";
import type Person from "./Person";
import type _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class _Person_stuff_Validators extends PartyValidators {

    constructor(type: _Person_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Person_stuff_TypeInfo;
    }

    validateProperties(val: any) {
    }

    validateRoleCount(val: integer) {
    }

    validateEmployee(val: boolean) {
    }

    validateBankCount(val: integer) {
    }

    validateGender(val: char) {
    }

    validatePronoun(val: string) {
    }

    validateSexualOrientation(val: string) {
    }

    validateHomeland(val: string) {
    }

    validatePassport(val: string) {
    }

    validateSsn(val: string) {
    }

    validateDln(val: string) {
    }

    validateMother(val: Person) {
    }

    validateFather(val: Person) {
    }

}

export default _Person_stuff_Validators;
