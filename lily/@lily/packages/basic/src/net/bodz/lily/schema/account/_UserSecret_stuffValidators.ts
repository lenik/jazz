import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { User } from "./User";

export class _UserSecret_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validatePassword(val: string) {
    }

    validateQuestion(val: string) {
    }

    validateAnswer(val: string) {
    }

    validateUser(val: User) {
    }

}

export default _UserSecret_stuffValidators;
