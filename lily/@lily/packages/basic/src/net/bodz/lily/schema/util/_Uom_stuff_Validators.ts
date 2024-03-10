import type { double } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoCodeValidators from "../../concrete/CoCodeValidators";
import type Uom from "./Uom";
import type _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class _Uom_stuff_Validators extends CoCodeValidators {

    constructor(type: _Uom_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Uom_stuff_TypeInfo;
    }

    validateProperty(val: string) {
    }

    validateScale(val: double) {
    }

    validateStandard(val: Uom) {
    }

}

export default _Uom_stuff_Validators;
