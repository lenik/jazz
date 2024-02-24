import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleTalkVoteTypeInfo from "./ArticleTalkVoteTypeInfo";
import _ArticleTalkVote_stuff_Validators from "./_ArticleTalkVote_stuff_Validators";

export class ArticleTalkVoteValidators extends _ArticleTalkVote_stuff_Validators {

    constructor(type: ArticleTalkVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleTalkVoteTypeInfo;
    }

}

export default ArticleTalkVoteValidators;
