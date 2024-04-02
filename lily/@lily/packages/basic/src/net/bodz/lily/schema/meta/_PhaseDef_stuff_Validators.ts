import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type SchemaDef from "./SchemaDef";
import type _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class _PhaseDef_stuff_Validators extends IdEntityValidators {

    constructor(type: _PhaseDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PhaseDef_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateSchema(val: SchemaDef) {
    }

}

export default _PhaseDef_stuff_Validators;
