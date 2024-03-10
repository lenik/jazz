import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestQuestionTalkTypeInfo from "./TestQuestionTalkTypeInfo";
import _TestQuestionTalk_stuff_Validators from "./_TestQuestionTalk_stuff_Validators";

export class TestQuestionTalkValidators extends _TestQuestionTalk_stuff_Validators {

    constructor(type: TestQuestionTalkTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestQuestionTalkTypeInfo;
    }

}

export default TestQuestionTalkValidators;
