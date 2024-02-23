import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoTalkValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkValidators";

import { TypeParamType } from "../../meta/TypeParamType";
import { Post } from "./Post";

export class _PostTalk_stuffValidators extends CoTalkValidators {
    validateFormArguments(val: string) {
    }

    validatePost(val: Post) {
    }

}

export default _PostTalk_stuffValidators;
