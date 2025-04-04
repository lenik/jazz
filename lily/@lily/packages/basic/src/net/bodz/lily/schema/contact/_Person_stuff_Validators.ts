import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type Gender from "./Gender";
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

    validateRoleCount(val: int) {
    }

    validateEmployee(val: boolean) {
    }

    validateBankCount(val: int) {
    }

    validateGender(val: Gender) {
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
