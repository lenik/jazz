import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

export class _UserType_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateDummy(val: integer) {
    }

}

export default _UserType_stuffValidators;
