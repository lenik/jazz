import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArticleTalkTypeInfo from "./ArticleTalkTypeInfo";
import _ArticleTalk_stuff_Validators from "./_ArticleTalk_stuff_Validators";

export class ArticleTalkValidators extends _ArticleTalk_stuff_Validators {

    constructor(type: ArticleTalkTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleTalkTypeInfo;
    }

}

export default ArticleTalkValidators;
