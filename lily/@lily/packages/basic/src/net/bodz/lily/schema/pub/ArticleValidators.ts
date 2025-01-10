import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
