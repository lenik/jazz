import { ValidateResult } from "skel01-core/src/ui/types";

import CoTagValidators from "../../concrete/CoTagValidators";
import type _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

export class _ArticleTagType_stuff_Validators extends CoTagValidators {

    constructor(type: _ArticleTagType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleTagType_stuff_TypeInfo;
    }

}

export default _ArticleTagType_stuff_Validators;
