import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import VoteRecordValidators from "../../concrete/VoteRecordValidators";
import type PostTalk from "./PostTalk";
import type _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class _PostTalkVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _PostTalkVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostTalkVote_stuff_TypeInfo;
    }

    validateVoteScore(val: integer) {
    }

    validateParent(val: PostTalk) {
    }

}

export default _PostTalkVote_stuff_Validators;
