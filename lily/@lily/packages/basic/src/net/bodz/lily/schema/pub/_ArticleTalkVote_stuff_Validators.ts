import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import VoteRecordValidators from "../../concrete/VoteRecordValidators";
import type ArticleTalk from "./ArticleTalk";
import type _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

export class _ArticleTalkVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _ArticleTalkVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleTalkVote_stuff_TypeInfo;
    }

    validateVoteScore(val: int) {
    }

    validateParent(val: ArticleTalk) {
    }

}

export default _ArticleTalkVote_stuff_Validators;
