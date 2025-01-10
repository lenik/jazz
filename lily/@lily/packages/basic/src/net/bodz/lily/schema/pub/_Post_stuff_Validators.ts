import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoMessageValidators from "../../concrete/CoMessageValidators";
import type Post from "./Post";
import type PostCategory from "./PostCategory";
import type _Post_stuff_TypeInfo from "./_Post_stuff_TypeInfo";

export class _Post_stuff_Validators extends CoMessageValidators {

    constructor(type: _Post_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Post_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateFavCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateHateCount(val: int) {
    }

    validateMessageCount(val: int) {
    }

    validatePlugins(val: JsonVariant) {
    }

    validateParent(val: Post) {
    }

    validateCategory(val: PostCategory) {
    }

}

export default _Post_stuff_Validators;
