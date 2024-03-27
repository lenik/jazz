import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoCategoryValidators from "@lily/basic/src/net/bodz/lily/concrete/CoCategoryValidators";

import type _IssueCategory_stuff_TypeInfo from "./_IssueCategory_stuff_TypeInfo";

export class _IssueCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _IssueCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _IssueCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _IssueCategory_stuff_Validators;