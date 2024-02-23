import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { ApiType } from "./ApiType";
import { VApp } from "./VApp";

export class _VApiCredit_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCredit(val: BigInteger) {
    }

    validateApi(val: ApiType) {
    }

    validateApp(val: VApp) {
    }

}

export default _VApiCredit_stuff_Validators;
