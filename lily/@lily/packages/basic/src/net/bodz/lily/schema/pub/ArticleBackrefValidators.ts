import { ValidateResult } from "skel01-core/src/ui/types";

import type ArticleBackrefTypeInfo from "./ArticleBackrefTypeInfo";
import _ArticleBackref_stuff_Validators from "./_ArticleBackref_stuff_Validators";

export class ArticleBackrefValidators extends _ArticleBackref_stuff_Validators {

    constructor(type: ArticleBackrefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleBackrefTypeInfo;
    }

}

export default ArticleBackrefValidators;
