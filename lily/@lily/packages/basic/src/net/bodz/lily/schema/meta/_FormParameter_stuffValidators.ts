import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { FormDef } from "./FormDef";

export class _FormParameter_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateValue(val: string) {
    }

    validateForm(val: FormDef) {
    }

}

export default _FormParameter_stuffValidators;
