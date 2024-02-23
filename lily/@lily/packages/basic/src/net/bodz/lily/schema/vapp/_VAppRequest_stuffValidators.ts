import { Moment } from "moment";

import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { User } from "../account/User";
import { FormDef } from "../meta/FormDef";

export class _VAppRequest_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateBeginTime(val: Moment) {
    }

    validateEndTime(val: Moment) {
    }

    validateYear(val: integer) {
    }

    validateSubject(val: string) {
    }

    validateRawText(val: string) {
    }

    validateFormArguments(val: string) {
    }

    validateDummy(val: integer) {
    }

    validateOp(val: User) {
    }

    validateForm(val: FormDef) {
    }

}

export default _VAppRequest_stuffValidators;
