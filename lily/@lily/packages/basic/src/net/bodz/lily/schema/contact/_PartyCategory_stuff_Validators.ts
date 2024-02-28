import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoCategoryValidators from "../../concrete/CoCategoryValidators";
import type _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

export class _PartyCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _PartyCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PartyCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _PartyCategory_stuff_Validators;
