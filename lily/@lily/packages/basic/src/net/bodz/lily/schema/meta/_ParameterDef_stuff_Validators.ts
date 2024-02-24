import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type SchemaDef from "./SchemaDef";
import type _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class _ParameterDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _ParameterDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ParameterDef_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateSchema(val: SchemaDef) {
    }

}

export default _ParameterDef_stuff_Validators;
