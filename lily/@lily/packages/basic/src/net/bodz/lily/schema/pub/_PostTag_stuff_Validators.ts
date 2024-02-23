import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Post } from "./Post";
import { PostTagType } from "./PostTagType";

export class _PostTag_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateTag(val: PostTagType) {
    }

    validatePost(val: Post) {
    }

}

export default _PostTag_stuff_Validators;
