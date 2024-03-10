import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestPaperItemTypeInfo from "./TestPaperItemTypeInfo";
import _TestPaperItem_stuff_Validators from "./_TestPaperItem_stuff_Validators";

export class TestPaperItemValidators extends _TestPaperItem_stuff_Validators {

    constructor(type: TestPaperItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestPaperItemTypeInfo;
    }

}

export default TestPaperItemValidators;
