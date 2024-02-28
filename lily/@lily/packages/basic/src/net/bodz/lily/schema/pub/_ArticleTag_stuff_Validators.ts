import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Article from "./Article";
import type ArticleTagType from "./ArticleTagType";
import type _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

export class _ArticleTag_stuff_Validators extends CoEntityValidators {

    constructor(type: _ArticleTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleTag_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateTag(val: ArticleTagType) {
    }

    validateArticle(val: Article) {
    }

}

export default _ArticleTag_stuff_Validators;
