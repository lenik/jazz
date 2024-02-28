import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type PostCategory from "./PostCategory";
import type _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

export class _PostCategory_stuff_Validators extends CoEntityValidators {

    constructor(type: _PostCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostCategory_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateName(val: string) {
    }

    validateDepth(val: int) {
    }

    validateRefCount(val: int) {
    }

    validateParent(val: PostCategory) {
    }

}

export default _PostCategory_stuff_Validators;
