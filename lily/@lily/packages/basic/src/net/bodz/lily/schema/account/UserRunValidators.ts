import type { int } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";

import type UserRunTypeInfo from "./UserRunTypeInfo";
import _UserRun_stuff_Validators from "./_UserRun_stuff_Validators";

export class UserRunValidators extends _UserRun_stuff_Validators {

    constructor(type: UserRunTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserRunTypeInfo;
    }

    validateActiveTime(val: OffsetDateTime) {
    }

    validateStateText(val: string) {
    }

}

export default UserRunValidators;
