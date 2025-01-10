import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoCategoryValidators from "../../concrete/CoCategoryValidators";
import type SchemaDef from "./SchemaDef";
import type _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class _CategoryDef_stuff_Validators extends CoCategoryValidators {

    constructor(type: _CategoryDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CategoryDef_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateSchema(val: SchemaDef) {
    }

}

export default _CategoryDef_stuff_Validators;
