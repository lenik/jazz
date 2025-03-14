import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type ArticleTagTypeInfo from "./ArticleTagTypeInfo";
import _ArticleTag_stuff_Validators from "./_ArticleTag_stuff_Validators";

export class ArticleTagValidators extends _ArticleTag_stuff_Validators {

    constructor(type: ArticleTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleTagTypeInfo;
    }

}

export default ArticleTagValidators;
