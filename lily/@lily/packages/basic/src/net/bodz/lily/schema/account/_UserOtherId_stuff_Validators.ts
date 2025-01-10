import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type User from "./User";
import type UserOtherIdType from "./UserOtherIdType";
import type _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class _UserOtherId_stuff_Validators extends CoImagedValidators {

    constructor(type: _UserOtherId_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserOtherId_stuff_TypeInfo;
    }

    validateBeginTime(val: OffsetDateTime) {
    }

    validateEndTime(val: OffsetDateTime) {
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
