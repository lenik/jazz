import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type DiaryReview from "./DiaryReview";
import type _DiaryReviewVote_stuff_TypeInfo from "./_DiaryReviewVote_stuff_TypeInfo";

export class _DiaryReviewVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _DiaryReviewVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryReviewVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: DiaryReview) {
    }

}

export default _DiaryReviewVote_stuff_Validators;
