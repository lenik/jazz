import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoCodeValidators from "../../concrete/CoCodeValidators";
import type _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

export class _SchemaDef_stuff_Validators extends CoCodeValidators {

    constructor(type: _SchemaDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SchemaDef_stuff_TypeInfo;
    }

    validateDummy(val: int) {
    }

}

export default _SchemaDef_stuff_Validators;
