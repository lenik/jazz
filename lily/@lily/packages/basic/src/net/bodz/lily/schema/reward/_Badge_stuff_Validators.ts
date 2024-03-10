import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class _Badge_stuff_Validators extends CoImagedValidators {

    constructor(type: _Badge_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Badge_stuff_TypeInfo;
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

}

export default _Badge_stuff_Validators;
