import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { TagGroupDef } from "./TagGroupDef";

export class _TagDef_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateTagGroup(val: TagGroupDef) {
    }

}

export default _TagDef_stuffValidators;
