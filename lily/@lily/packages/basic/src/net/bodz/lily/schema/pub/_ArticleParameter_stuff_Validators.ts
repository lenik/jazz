import type { double, integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Article from "./Article";
import type ArticleParameterType from "./ArticleParameterType";
import type _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class _ArticleParameter_stuff_Validators extends CoEntityValidators {

    constructor(type: _ArticleParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleParameter_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateIval(val: integer) {
    }

    validateFval(val: double) {
    }

    validateSval(val: string) {
    }

    validateArticle(val: Article) {
    }

    validateParameter(val: ArticleParameterType) {
    }

}

export default _ArticleParameter_stuff_Validators;
