import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { CategoryDef } from "./CategoryDef";
import { SchemaDef } from "./SchemaDef";

export class _CategoryDef_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateDepth(val: integer) {
    }

    validateRefCount(val: integer) {
    }

    validateSchema(val: SchemaDef) {
    }

    validateParent(val: CategoryDef) {
    }

}

export default _CategoryDef_stuff_Validators;
