import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordValidators";

import { Post } from "./Post";

export class _PostVote_stuff_Validators extends VoteRecordValidators {
    validateVoteScore(val: integer) {
    }

    validateParent(val: Post) {
    }

}

export default _PostVote_stuff_Validators;
