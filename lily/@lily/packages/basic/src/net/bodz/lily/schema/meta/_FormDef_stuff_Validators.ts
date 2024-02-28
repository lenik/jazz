import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type SchemaDef from "./SchemaDef";
import type _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class _FormDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _FormDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FormDef_stuff_TypeInfo;
    }

    validateId(val: int) {
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

export default _FormDef_stuff_Validators;
