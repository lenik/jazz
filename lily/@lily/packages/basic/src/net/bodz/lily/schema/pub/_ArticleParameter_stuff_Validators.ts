import type { double, int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type Article from "./Article";
import type ArticleParameterType from "./ArticleParameterType";
import type _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class _ArticleParameter_stuff_Validators extends IdEntityValidators {

    constructor(type: _ArticleParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleParameter_stuff_TypeInfo;
    }

    validateIval(val: int) {
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
