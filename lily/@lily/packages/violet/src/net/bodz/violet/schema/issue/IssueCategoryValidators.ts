import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type IssueCategoryTypeInfo from "./IssueCategoryTypeInfo";
import _IssueCategory_stuff_Validators from "./_IssueCategory_stuff_Validators";

export class IssueCategoryValidators extends _IssueCategory_stuff_Validators {

    constructor(type: IssueCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssueCategoryTypeInfo;
    }

}

export default IssueCategoryValidators;
