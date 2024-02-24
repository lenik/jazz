import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PostParameterTypeTypeInfo from "./PostParameterTypeTypeInfo";
import _PostParameterType_stuff_Validators from "./_PostParameterType_stuff_Validators";

export class PostParameterTypeValidators extends _PostParameterType_stuff_Validators {

    constructor(type: PostParameterTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PostParameterTypeTypeInfo;
    }

}

export default PostParameterTypeValidators;
