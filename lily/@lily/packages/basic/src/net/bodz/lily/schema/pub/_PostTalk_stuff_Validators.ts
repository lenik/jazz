import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoTalkValidators from "../../concrete/CoTalkValidators";
import type Post from "./Post";
import type _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class _PostTalk_stuff_Validators extends CoTalkValidators {

    constructor(type: _PostTalk_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostTalk_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validatePost(val: Post) {
    }

}

export default _PostTalk_stuff_Validators;
