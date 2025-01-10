import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type Post from "./Post";
import type PostTagType from "./PostTagType";
import type _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class _PostTag_stuff_Validators extends IdEntityValidators {

    constructor(type: _PostTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostTag_stuff_TypeInfo;
    }

    validateTag(val: PostTagType) {
    }

    validatePost(val: Post) {
    }

}

export default _PostTag_stuff_Validators;
