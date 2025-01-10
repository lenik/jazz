import { ValidateResult } from "skel01-core/src/ui/types";

import type TestQuestionVoteTypeInfo from "./TestQuestionVoteTypeInfo";
import _TestQuestionVote_stuff_Validators from "./_TestQuestionVote_stuff_Validators";

export class TestQuestionVoteValidators extends _TestQuestionVote_stuff_Validators {

    constructor(type: TestQuestionVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestQuestionVoteTypeInfo;
    }

}

export default TestQuestionVoteValidators;
