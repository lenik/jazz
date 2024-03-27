import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabStdTestCategoryTypeInfo from "./FabStdTestCategoryTypeInfo";
import _FabStdTestCategory_stuff_Validators from "./_FabStdTestCategory_stuff_Validators";

export class FabStdTestCategoryValidators extends _FabStdTestCategory_stuff_Validators {

    constructor(type: FabStdTestCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabStdTestCategoryTypeInfo;
    }

}

export default FabStdTestCategoryValidators;