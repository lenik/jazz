import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleVoteTypeInfo from "./ArticleVoteTypeInfo";
import _ArticleVote_stuff_Validators from "./_ArticleVote_stuff_Validators";

export class ArticleVoteValidators extends _ArticleVote_stuff_Validators {

    constructor(type: ArticleVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleVoteTypeInfo;
    }

}

export default ArticleVoteValidators;
