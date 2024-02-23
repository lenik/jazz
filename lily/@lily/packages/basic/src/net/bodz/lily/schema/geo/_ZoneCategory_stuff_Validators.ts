import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { ZoneCategory } from "./ZoneCategory";

export class _ZoneCategory_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateDepth(val: integer) {
    }

    validateRefCount(val: integer) {
    }

    validateParent(val: ZoneCategory) {
    }

}

export default _ZoneCategory_stuff_Validators;
