import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import VoteRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type TestQuestion from "./TestQuestion";
import type _TestQuestionVote_stuff_TypeInfo from "./_TestQuestionVote_stuff_TypeInfo";

export class _TestQuestionVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _TestQuestionVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestQuestionVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: TestQuestion) {
    }

}

export default _TestQuestionVote_stuff_Validators;
