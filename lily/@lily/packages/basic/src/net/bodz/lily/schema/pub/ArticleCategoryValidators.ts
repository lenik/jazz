import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleCategoryTypeInfo from "./ArticleCategoryTypeInfo";
import _ArticleCategory_stuff_Validators from "./_ArticleCategory_stuff_Validators";

export class ArticleCategoryValidators extends _ArticleCategory_stuff_Validators {

    constructor(type: ArticleCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleCategoryTypeInfo;
    }

}

export default ArticleCategoryValidators;
