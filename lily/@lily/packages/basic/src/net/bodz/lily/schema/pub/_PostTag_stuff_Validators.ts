import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Post from "./Post";
import type PostTagType from "./PostTagType";
import type _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class _PostTag_stuff_Validators extends CoEntityValidators {

    constructor(type: _PostTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostTag_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateTag(val: PostTagType) {
    }

    validatePost(val: Post) {
    }

}

export default _PostTag_stuff_Validators;
