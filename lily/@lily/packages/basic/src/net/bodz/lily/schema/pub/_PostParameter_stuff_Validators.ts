import type { double, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type Post from "./Post";
import type PostParameterType from "./PostParameterType";
import type _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class _PostParameter_stuff_Validators extends IdEntityValidators {

    constructor(type: _PostParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostParameter_stuff_TypeInfo;
    }

    validateIval(val: int) {
    }

    validateFval(val: double) {
    }

    validateSval(val: string) {
    }

    validatePost(val: Post) {
    }

    validateParameter(val: PostParameterType) {
    }

}

export default _PostParameter_stuff_Validators;
