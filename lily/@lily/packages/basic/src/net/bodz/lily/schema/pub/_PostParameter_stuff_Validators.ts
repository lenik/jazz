import type { double, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Post from "./Post";
import type PostParameterType from "./PostParameterType";
import type _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class _PostParameter_stuff_Validators extends CoEntityValidators {

    constructor(type: _PostParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostParameter_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateIval(val: integer) {
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
