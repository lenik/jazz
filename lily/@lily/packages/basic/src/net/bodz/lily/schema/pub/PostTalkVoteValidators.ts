import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostTalkVoteTypeInfo from "./PostTalkVoteTypeInfo";
import _PostTalkVote_stuff_Validators from "./_PostTalkVote_stuff_Validators";

export class PostTalkVoteValidators extends _PostTalkVote_stuff_Validators {

    constructor(type: PostTalkVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostTalkVoteTypeInfo;
    }

}

export default PostTalkVoteValidators;
