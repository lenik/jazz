import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordValidators";

import { ArticleTalk } from "./ArticleTalk";

export class _ArticleTalkVote_stuffValidators extends VoteRecordValidators {
    validateVoteScore(val: integer) {
    }

    validateParent(val: ArticleTalk) {
    }

}

export default _ArticleTalkVote_stuffValidators;
