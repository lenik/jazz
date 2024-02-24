import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostTalkTypeInfo from "./PostTalkTypeInfo";
import _PostTalk_stuff_Validators from "./_PostTalk_stuff_Validators";

export class PostTalkValidators extends _PostTalk_stuff_Validators {

    constructor(type: PostTalkTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostTalkTypeInfo;
    }

}

export default PostTalkValidators;
