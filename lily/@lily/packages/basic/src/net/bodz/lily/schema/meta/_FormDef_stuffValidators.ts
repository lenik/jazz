import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { SchemaDef } from "./SchemaDef";

export class _FormDef_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateSubject(val: string) {
    }

    validateRawText(val: string) {
    }

    validateSchema(val: SchemaDef) {
    }

}

export default _FormDef_stuffValidators;
