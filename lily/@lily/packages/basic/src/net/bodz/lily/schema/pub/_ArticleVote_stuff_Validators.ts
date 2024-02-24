import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import VoteRecordValidators from "../../concrete/VoteRecordValidators";
import type Article from "./Article";
import type _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class _ArticleVote_stuff_Validators extends VoteRecordValidators {

    constructor(type: _ArticleVote_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleVote_stuff_TypeInfo;
    }

    validateVoteScore(val: integer) {
    }

    validateParent(val: Article) {
    }

}

export default _ArticleVote_stuff_Validators;
