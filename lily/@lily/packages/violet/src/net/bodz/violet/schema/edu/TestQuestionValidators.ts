import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
