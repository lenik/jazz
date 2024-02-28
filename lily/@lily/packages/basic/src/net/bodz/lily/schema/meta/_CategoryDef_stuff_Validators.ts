import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type CategoryDef from "./CategoryDef";
import type SchemaDef from "./SchemaDef";
import type _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class _CategoryDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _CategoryDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CategoryDef_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateCode(val: string) {
    }

    validateDepth(val: int) {
    }

    validateRefCount(val: int) {
    }

    validateSchema(val: SchemaDef) {
    }

    validateParent(val: CategoryDef) {
    }

}

export default _CategoryDef_stuff_Validators;
