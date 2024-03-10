import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoCategoryValidators from "@lily/basic/src/net/bodz/lily/concrete/CoCategoryValidators";

import type _SalesCategory_stuff_TypeInfo from "./_SalesCategory_stuff_TypeInfo";

export class _SalesCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _SalesCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SalesCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _SalesCategory_stuff_Validators;
