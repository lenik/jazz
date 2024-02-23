import type { integer, long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoMessageValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessageValidators";

import { Post } from "./Post";
import { PostCategory } from "./PostCategory";

export class _Post_stuffValidators extends CoMessageValidators {
    validateFormArguments(val: string) {
    }

    validateFavCount(val: integer) {
    }

    validateVoteCount(val: integer) {
    }

    validateHateCount(val: integer) {
    }

    validateMessageCount(val: integer) {
    }

    validatePlugins(val: any) {
    }

    validateParent(val: Post) {
    }

    validateCategory(val: PostCategory) {
    }

}

export default _Post_stuffValidators;
