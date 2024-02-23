import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { User } from "./User";

export class _UserRun_stuff_Validators extends CoEntityValidators {
    validateScore(val: integer) {
    }

    validateLastLoginTime(val: Date) {
    }

    validateLastLoginIP(val: string) {
    }

    validateUser(val: User) {
    }

}

export default _UserRun_stuff_Validators;
