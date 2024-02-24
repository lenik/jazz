import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type User from "./User";
import type UserOtherIdType from "./UserOtherIdType";
import type _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class _UserOtherId_stuff_Validators extends CoEntityValidators {

    constructor(type: _UserOtherId_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserOtherId_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateEndTime(val: ZonedDateTime) {
    }

    validateYear(val: integer) {
    }

    validateOtherId(val: string) {
    }

    validateAuth(val: any) {
    }

    validateType(val: UserOtherIdType) {
    }

    validateUser(val: User) {
    }

}

export default _UserOtherId_stuff_Validators;
