import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { ExternalSite } from "./ExternalSite";

export class _ExternalSite_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateDepth(val: integer) {
    }

    validateUrlfmt(val: string) {
    }

    validateBonus(val: integer) {
    }

    validateCount(val: integer) {
    }

    validateParent(val: ExternalSite) {
    }

}

export default _ExternalSite_stuff_Validators;
