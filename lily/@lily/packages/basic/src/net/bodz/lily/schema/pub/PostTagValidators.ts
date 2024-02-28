import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostTagTypeInfo from "./PostTagTypeInfo";
import _PostTag_stuff_Validators from "./_PostTag_stuff_Validators";

export class PostTagValidators extends _PostTag_stuff_Validators {

    constructor(type: PostTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostTagTypeInfo;
    }

}

export default PostTagValidators;
