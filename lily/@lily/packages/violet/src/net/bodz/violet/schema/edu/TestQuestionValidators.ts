import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestQuestionTypeInfo from "./TestQuestionTypeInfo";
import _TestQuestion_stuff_Validators from "./_TestQuestion_stuff_Validators";

export class TestQuestionValidators extends _TestQuestion_stuff_Validators {

    constructor(type: TestQuestionTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestQuestionTypeInfo;
    }

}

export default TestQuestionValidators;
