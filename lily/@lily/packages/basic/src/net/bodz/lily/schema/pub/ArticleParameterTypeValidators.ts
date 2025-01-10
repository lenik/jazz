import { ValidateResult } from "skel01-core/src/ui/types";

import type ArticleParameterTypeTypeInfo from "./ArticleParameterTypeTypeInfo";
import _ArticleParameterType_stuff_Validators from "./_ArticleParameterType_stuff_Validators";

export class ArticleParameterTypeValidators extends _ArticleParameterType_stuff_Validators {

    constructor(type: ArticleParameterTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleParameterTypeTypeInfo;
    }

}

export default ArticleParameterTypeValidators;
