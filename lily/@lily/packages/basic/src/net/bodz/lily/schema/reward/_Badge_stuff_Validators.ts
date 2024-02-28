import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class _Badge_stuff_Validators extends CoEntityValidators {

    constructor(type: _Badge_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Badge_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateExpr(val: string) {
    }

    validateVal(val: int) {
    }

    validateLevels(val: int[]) {
    }

    validateDescend(val: boolean) {
    }

    validateTransient_(val: boolean) {
    }

    validateIndexed(val: boolean) {
    }

    validateImage(val: string) {
    }

}

export default _Badge_stuff_Validators;
