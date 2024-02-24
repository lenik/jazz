import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ArticleCategory from "./ArticleCategory";
import type _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class _ArticleCategory_stuff_Validators extends CoEntityValidators {

    constructor(type: _ArticleCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleCategory_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateName(val: string) {
    }

    validateDepth(val: integer) {
    }

    validateRefCount(val: integer) {
    }

    validateParent(val: ArticleCategory) {
    }

}

export default _ArticleCategory_stuff_Validators;
