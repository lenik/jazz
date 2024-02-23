import { Moment } from "moment";

import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import _UserRun_stuffValidators from "./_UserRun_stuffValidators";

export class UserRunValidators extends _UserRun_stuffValidators {
    validateActiveTime(val: Moment) {
    }
    validateStateText(val: string) {
    }

}

export default UserRunValidators;
