import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostCategoryTypeInfo from "./PostCategoryTypeInfo";
import _PostCategory_stuff_Validators from "./_PostCategory_stuff_Validators";

export class PostCategoryValidators extends _PostCategory_stuff_Validators {

    constructor(type: PostCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostCategoryTypeInfo;
    }

}

export default PostCategoryValidators;
