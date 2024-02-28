import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type User from "../account/User";
import type FormDef from "../meta/FormDef";
import type _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class _VAppRequest_stuff_Validators extends CoEntityValidators {

    constructor(type: _VAppRequest_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppRequest_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateCode(val: string) {
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateEndTime(val: ZonedDateTime) {
    }

    validateYear(val: int) {
    }

    validateSubject(val: string) {
    }

    validateRawText(val: string) {
    }

    validateFormArguments(val: string) {
    }

    validateDummy(val: int) {
    }

    validateOp(val: User) {
    }

    validateForm(val: FormDef) {
    }

}

export default _VAppRequest_stuff_Validators;
