import { ValidateResult } from "skel01-core/src/ui/types";

import type TestQuestionTalkVoteTypeInfo from "./TestQuestionTalkVoteTypeInfo";
import _TestQuestionTalkVote_stuff_Validators from "./_TestQuestionTalkVote_stuff_Validators";

export class TestQuestionTalkVoteValidators extends _TestQuestionTalkVote_stuff_Validators {

    constructor(type: TestQuestionTalkVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestQuestionTalkVoteTypeInfo;
    }

}

export default TestQuestionTalkVoteValidators;
