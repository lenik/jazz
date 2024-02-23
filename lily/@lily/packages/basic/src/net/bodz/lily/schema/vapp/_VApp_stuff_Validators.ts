import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { VAppCat } from "./VAppCat";
import { VAppRequest } from "./VAppRequest";

export class _VApp_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateSecret(val: string) {
    }

    validateCategory(val: VAppCat) {
    }

    validateReq(val: VAppRequest) {
    }

}

export default _VApp_stuff_Validators;
