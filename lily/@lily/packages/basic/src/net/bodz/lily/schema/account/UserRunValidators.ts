import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type UserRunTypeInfo from "./UserRunTypeInfo";
import _UserRun_stuff_Validators from "./_UserRun_stuff_Validators";

export class UserRunValidators extends _UserRun_stuff_Validators {

    constructor(type: UserRunTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserRunTypeInfo;
    }

    validateActiveTime(val: ZonedDateTime) {
    }

    validateStateText(val: string) {
    }

}

export default UserRunValidators;
