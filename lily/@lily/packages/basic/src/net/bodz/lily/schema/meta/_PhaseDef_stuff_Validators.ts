import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type SchemaDef from "./SchemaDef";
import type _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class _PhaseDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _PhaseDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PhaseDef_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateSchema(val: SchemaDef) {
    }

}

export default _PhaseDef_stuff_Validators;
