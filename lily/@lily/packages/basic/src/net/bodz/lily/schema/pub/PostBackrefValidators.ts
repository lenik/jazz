import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostBackrefTypeInfo from "./PostBackrefTypeInfo";
import _PostBackref_stuff_Validators from "./_PostBackref_stuff_Validators";

export class PostBackrefValidators extends _PostBackref_stuff_Validators {

    constructor(type: PostBackrefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostBackrefTypeInfo;
    }

}

export default PostBackrefValidators;
