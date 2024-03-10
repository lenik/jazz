import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordValidators";

import type Diary from "./Diary";
import type _DiaryVote_stuff_TypeInfo from "./_DiaryVote_stuff_TypeInfo";

export class _DiaryVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _DiaryVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: Diary) {
    }

}

export default _DiaryVote_stuff_Validators;
