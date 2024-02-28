import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ZoneCategory from "./ZoneCategory";
import type _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class _ZoneCategory_stuff_Validators extends CoEntityValidators {

    constructor(type: _ZoneCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ZoneCategory_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateName(val: string) {
    }

    validateDepth(val: int) {
    }

    validateRefCount(val: int) {
    }

    validateParent(val: ZoneCategory) {
    }

}

export default _ZoneCategory_stuff_Validators;
