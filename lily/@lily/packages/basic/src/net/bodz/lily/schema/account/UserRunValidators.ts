import { Moment } from "moment";

import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import _UserRun_stuff_Validators from "./_UserRun_stuff_Validators";

export class UserRunValidators extends _UserRun_stuff_Validators {
    validateActiveTime(val: Moment) {
    }
    validateStateText(val: string) {
    }

}

export default UserRunValidators;
