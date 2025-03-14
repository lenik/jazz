import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type Zone from "./Zone";
import type ZoneCategory from "./ZoneCategory";
import type _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class _Zone_stuff_Validators extends CoImagedValidators {

    constructor(type: _Zone_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Zone_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateCountry(val: string) {
    }

    validateDepth(val: int) {
    }

    validateTelCode(val: string) {
    }

    validatePostCode(val: string) {
    }

    validateData(val: JsonVariant) {
    }

    validateParent(val: Zone) {
    }

    validateCategory(val: ZoneCategory) {
    }

}

export default _Zone_stuff_Validators;
