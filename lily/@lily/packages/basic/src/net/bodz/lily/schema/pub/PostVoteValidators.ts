import { ValidateResult } from "skel01-core/src/ui/types";

import type PostVoteTypeInfo from "./PostVoteTypeInfo";
import _PostVote_stuff_Validators from "./_PostVote_stuff_Validators";

export class PostVoteValidators extends _PostVote_stuff_Validators {

    constructor(type: PostVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostVoteTypeInfo;
    }

}

export default PostVoteValidators;
