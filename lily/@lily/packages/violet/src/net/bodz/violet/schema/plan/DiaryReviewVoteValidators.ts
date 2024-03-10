import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryReviewVoteTypeInfo from "./DiaryReviewVoteTypeInfo";
import _DiaryReviewVote_stuff_Validators from "./_DiaryReviewVote_stuff_Validators";

export class DiaryReviewVoteValidators extends _DiaryReviewVote_stuff_Validators {

    constructor(type: DiaryReviewVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryReviewVoteTypeInfo;
    }

}

export default DiaryReviewVoteValidators;
