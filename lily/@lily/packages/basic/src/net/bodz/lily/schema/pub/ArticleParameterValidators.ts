import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleParameterTypeInfo from "./ArticleParameterTypeInfo";
import _ArticleParameter_stuff_Validators from "./_ArticleParameter_stuff_Validators";

export class ArticleParameterValidators extends _ArticleParameter_stuff_Validators {

    constructor(type: ArticleParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleParameterTypeInfo;
    }

}

export default ArticleParameterValidators;
