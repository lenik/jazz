import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type SchemaDef from "./SchemaDef";
import type _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

export class _TagGroupDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _TagGroupDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TagGroupDef_stuff_TypeInfo;
    }

    validateId(val: int) {
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
