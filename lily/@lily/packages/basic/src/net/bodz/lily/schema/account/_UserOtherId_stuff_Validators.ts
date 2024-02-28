import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
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

    validateId(val: int) {
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateEndTime(val: ZonedDateTime) {
    }

    validateYear(val: int) {
    }

    validateOtherId(val: string) {
    }

    validateAuth(val: JsonVariant) {
    }

    validateType(val: UserOtherIdType) {
    }

    validateUser(val: User) {
    }

}

export default _UserOtherId_stuff_Validators;
