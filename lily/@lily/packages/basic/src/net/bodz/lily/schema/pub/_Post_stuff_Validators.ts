import type { integer, long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

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

export default _Post_stuff_Validators;
