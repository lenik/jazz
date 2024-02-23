import type { double, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { Post } from "./Post";
import { PostParameterType } from "./PostParameterType";

export class _PostParameter_stuff_Validators extends CoEntityValidators {
    validateId(val: integer) {
    }

    validateIval(val: integer) {
    }

    validateFval(val: double) {
    }

    validateSval(val: string) {
    }

    validatePost(val: Post) {
    }

    validateParameter(val: PostParameterType) {
    }

}

export default _PostParameter_stuff_Validators;
