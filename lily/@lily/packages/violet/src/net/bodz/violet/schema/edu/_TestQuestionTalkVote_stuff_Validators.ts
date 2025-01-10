import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import VoteRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type TestQuestionTalk from "./TestQuestionTalk";
import type _TestQuestionTalkVote_stuff_TypeInfo from "./_TestQuestionTalkVote_stuff_TypeInfo";

export class _TestQuestionTalkVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _TestQuestionTalkVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestQuestionTalkVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: TestQuestionTalk) {
    }

}

export default _TestQuestionTalkVote_stuff_Validators;
