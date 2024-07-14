import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoParameterValidators from "../../concrete/CoParameterValidators";
import type _ArticleParameterType_stuff_TypeInfo from "./_ArticleParameterType_stuff_TypeInfo";

export class _ArticleParameterType_stuff_Validators extends CoParameterValidators {

    constructor(type: _ArticleParameterType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleParameterType_stuff_TypeInfo;
    }

}

export default _ArticleParameterType_stuff_Validators;
