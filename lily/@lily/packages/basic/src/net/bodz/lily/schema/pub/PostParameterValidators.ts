import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type PostParameterTypeInfo from "./PostParameterTypeInfo";
import _PostParameter_stuff_Validators from "./_PostParameter_stuff_Validators";

export class PostParameterValidators extends _PostParameter_stuff_Validators {

    constructor(type: PostParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostParameterTypeInfo;
    }

}

export default PostParameterValidators;
