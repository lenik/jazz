import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestApplyTypeInfo from "./TestApplyTypeInfo";
import _TestApply_stuff_Validators from "./_TestApply_stuff_Validators";

export class TestApplyValidators extends _TestApply_stuff_Validators {

    constructor(type: TestApplyTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestApplyTypeInfo;
    }

}

export default TestApplyValidators;