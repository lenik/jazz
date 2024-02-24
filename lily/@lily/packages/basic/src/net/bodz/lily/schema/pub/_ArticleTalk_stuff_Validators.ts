import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoTalkValidators from "../../concrete/CoTalkValidators";
import type Article from "./Article";
import type _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class _ArticleTalk_stuff_Validators extends CoTalkValidators {

    constructor(type: _ArticleTalk_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleTalk_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateArticle(val: Article) {
    }

}

export default _ArticleTalk_stuff_Validators;
