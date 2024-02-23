import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import VoteRecordValidators from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordValidators";

import { Article } from "./Article";

export class _ArticleVote_stuffValidators extends VoteRecordValidators {
    validateVoteScore(val: integer) {
    }

    validateParent(val: Article) {
    }

}

export default _ArticleVote_stuffValidators;
