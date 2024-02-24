import type { double, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Uom from "./Uom";
import type _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class _Uom_stuff_Validators extends CoEntityValidators {

    constructor(type: _Uom_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Uom_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateProp(val: string) {
    }

    validateScale(val: double) {
    }

    validateStd(val: Uom) {
    }

}

export default _Uom_stuff_Validators;
