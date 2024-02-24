import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleTypeInfo from "./ArticleTypeInfo";
import _Article_stuff_Validators from "./_Article_stuff_Validators";

export class ArticleValidators extends _Article_stuff_Validators {

    constructor(type: ArticleTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleTypeInfo;
    }

}

export default ArticleValidators;
