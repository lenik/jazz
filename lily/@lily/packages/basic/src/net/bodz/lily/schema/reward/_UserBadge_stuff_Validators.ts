import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { User } from "../account/User";
import { Badge } from "./Badge";

export class _UserBadge_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateBadge(val: Badge) {
    }

    validateUser(val: User) {
    }

}

export default _UserBadge_stuff_Validators;
