import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Zone from "./Zone";
import type ZoneCategory from "./ZoneCategory";
import type _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class _Zone_stuff_Validators extends CoEntityValidators {

    constructor(type: _Zone_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Zone_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateCode(val: string) {
    }

    validateCountry(val: string) {
    }

    validateDepth(val: integer) {
    }

    validateTelCode(val: string) {
    }

    validatePostCode(val: string) {
    }

    validateData(val: any) {
    }

    validateParent(val: Zone) {
    }

    validateCategory(val: ZoneCategory) {
    }

}

export default _Zone_stuff_Validators;
