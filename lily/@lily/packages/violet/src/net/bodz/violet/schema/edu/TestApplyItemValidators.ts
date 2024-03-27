import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestApplyItemTypeInfo from "./TestApplyItemTypeInfo";
import _TestApplyItem_stuff_Validators from "./_TestApplyItem_stuff_Validators";

export class TestApplyItemValidators extends _TestApplyItem_stuff_Validators {

    constructor(type: TestApplyItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestApplyItemTypeInfo;
    }

}

export default TestApplyItemValidators;