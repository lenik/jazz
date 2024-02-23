import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { PostCategory } from "./PostCategory";

export class _PostCategory_stuffValidators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateDepth(val: integer) {
    }

    validateRefCount(val: integer) {
    }

    validateParent(val: PostCategory) {
    }

}

export default _PostCategory_stuffValidators;
