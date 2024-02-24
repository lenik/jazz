import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleTagTypeTypeInfo from "./ArticleTagTypeTypeInfo";
import _ArticleTagType_stuff_Validators from "./_ArticleTagType_stuff_Validators";

export class ArticleTagTypeValidators extends _ArticleTagType_stuff_Validators {

    constructor(type: ArticleTagTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleTagTypeTypeInfo;
    }

}

export default ArticleTagTypeValidators;
