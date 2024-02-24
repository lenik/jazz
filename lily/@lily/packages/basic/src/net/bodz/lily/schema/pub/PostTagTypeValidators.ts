import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostTagTypeTypeInfo from "./PostTagTypeTypeInfo";
import _PostTagType_stuff_Validators from "./_PostTagType_stuff_Validators";

export class PostTagTypeValidators extends _PostTagType_stuff_Validators {

    constructor(type: PostTagTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostTagTypeTypeInfo;
    }

}

export default PostTagTypeValidators;
