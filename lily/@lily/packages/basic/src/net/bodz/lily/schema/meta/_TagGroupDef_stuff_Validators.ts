import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { SchemaDef } from "./SchemaDef";

export class _TagGroupDef_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateForTopic(val: boolean) {
    }

    validateForReply(val: boolean) {
    }

    validateSchema(val: SchemaDef) {
    }

}

export default _TagGroupDef_stuff_Validators;
