import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostTypeInfo from "./PostTypeInfo";
import _Post_stuff_Validators from "./_Post_stuff_Validators";

export class PostValidators extends _Post_stuff_Validators {

    constructor(type: PostTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostTypeInfo;
    }

}

export default PostValidators;
