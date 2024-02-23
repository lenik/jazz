import type { double, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Uom } from "./Uom";

export class _Uom_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateProp(val: string) {
    }

    validateScale(val: double) {
    }

    validateStd(val: Uom) {
    }

}

export default _Uom_stuffValidators;
