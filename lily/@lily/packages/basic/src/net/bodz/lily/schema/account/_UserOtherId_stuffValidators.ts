import { Moment } from "moment";

import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { User } from "./User";
import { UserOtherIdType } from "./UserOtherIdType";

export class _UserOtherId_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateBeginTime(val: Moment) {
    }

    validateEndTime(val: Moment) {
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

export default _UserOtherId_stuffValidators;
