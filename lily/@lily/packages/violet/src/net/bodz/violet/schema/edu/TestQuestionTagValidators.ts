import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestQuestionTagTypeInfo from "./TestQuestionTagTypeInfo";
import _TestQuestionTag_stuff_Validators from "./_TestQuestionTag_stuff_Validators";

export class TestQuestionTagValidators extends _TestQuestionTag_stuff_Validators {

    constructor(type: TestQuestionTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestQuestionTagTypeInfo;
    }

}

export default TestQuestionTagValidators;
