import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordValidators";

import { PostTalk } from "./PostTalk";

export class _PostTalkVote_stuff_Validators extends VoteRecordValidators {
    validateVoteScore(val: integer) {
    }

    validateParent(val: PostTalk) {
    }

}

export default _PostTalkVote_stuff_Validators;
