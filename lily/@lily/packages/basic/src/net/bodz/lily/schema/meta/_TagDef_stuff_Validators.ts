import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type TagGroupDef from "./TagGroupDef";
import type _TagDef_stuff_TypeInfo from "./_TagDef_stuff_TypeInfo";

export class _TagDef_stuff_Validators extends CoEntityValidators {

    constructor(type: _TagDef_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TagDef_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateTagGroup(val: TagGroupDef) {
    }

}

export default _TagDef_stuff_Validators;
