import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Zone } from "./Zone";
import { ZoneCategory } from "./ZoneCategory";

export class _Zone_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateCountry(val: string) {
    }

    validateDepth(val: integer) {
    }

    validateTelCode(val: string) {
    }

    validatePostCode(val: string) {
    }

    validateData(val: any) {
    }

    validateParent(val: Zone) {
    }

    validateCategory(val: ZoneCategory) {
    }

}

export default _Zone_stuffValidators;
