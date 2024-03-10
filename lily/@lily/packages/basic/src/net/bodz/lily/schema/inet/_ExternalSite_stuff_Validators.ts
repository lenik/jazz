import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoNodeValidators from "../../concrete/CoNodeValidators";
import type _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class _ExternalSite_stuff_Validators extends CoNodeValidators {

    constructor(type: _ExternalSite_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ExternalSite_stuff_TypeInfo;
    }

    validateUrlfmt(val: string) {
    }

    validateBonus(val: int) {
    }

    validateCount(val: int) {
    }

}

export default _ExternalSite_stuff_Validators;
