import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

export class _SchemaDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _SchemaDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SchemaDef_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateDummy(val: integer) {
    }

}

export default _SchemaDef_stuff_Validators;
